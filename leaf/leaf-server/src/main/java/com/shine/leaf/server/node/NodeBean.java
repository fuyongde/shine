package com.shine.leaf.server.node;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.shine.leaf.server.config.ZookeeperConfig;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/20 12:49
 * @desc 根据zookeeper分配节点信息
 */
@Component
public class NodeBean {

  private static Logger logger = LoggerFactory.getLogger(NodeBean.class);

  @Autowired
  private ZookeeperConfig zookeeperConfig;

  private static final String LEAF_ROOT_PATH = "/leaf";
  private static final String LEAF_NODE_PATH = "/node_";

  private static Cache<Integer, Integer> nodeCache = CacheBuilder.newBuilder().maximumSize(1).build();

  public int getNode() throws ExecutionException {
    return nodeCache.get(1, ()->Integer.valueOf(getZkNode().split("_")[1]));
  }

  private String getZkNode() throws SocketException, UnknownHostException {
    ZkClient zkClient = new ZkClient(zookeeperConfig.getAddress());

    boolean leafRootExist = zkClient.exists(LEAF_ROOT_PATH);

    String macAddress = HardwareUtils.getMacaddress();

    logger.info("跟节点是否存在：" + leafRootExist);

    if (!leafRootExist) {
      zkClient.createPersistent(LEAF_ROOT_PATH);
    }

    List<String> leafNodes = zkClient.getChildren(LEAF_ROOT_PATH);

    boolean nodeExist = false;

    String thisNode = null;

    String nodePath = LEAF_ROOT_PATH + LEAF_NODE_PATH;
    if (CollectionUtils.isNotEmpty(leafNodes)) {
      List<String> matchNodeList = leafNodes
          .stream()
          .filter(node->StringUtils.startsWith(node, LEAF_NODE_PATH.substring(1, LEAF_NODE_PATH.length())))
          .filter(node-> StringUtils.equals(zkClient.readData(LEAF_ROOT_PATH + "/" + node), macAddress))
          .collect(Collectors.toList());

      int count = matchNodeList.size();
      logger.info("节点数量：{}", count);

      if (count > 1) {
        // 删除所有符合条件的节点
        logger.info("删除多余的节点");
        matchNodeList.stream()
            .forEach(node->{
              if (StringUtils.equals(zkClient.readData(LEAF_ROOT_PATH + "/" + node), macAddress)) {
                zkClient.delete(LEAF_ROOT_PATH + "/" + node);
              }
            });
      } else if (count == 1) {
        nodeExist = true;
        thisNode = matchNodeList.get(0);
      }

    }

    if (!nodeExist) {
      thisNode = zkClient.createPersistentSequential(nodePath, macAddress);
      logger.info("创建该节点：{}", thisNode);
    }

    logger.info("该节点为：{}", thisNode);

    return thisNode;
  }
}
