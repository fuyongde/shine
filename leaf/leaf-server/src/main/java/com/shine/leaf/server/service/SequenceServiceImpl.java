package com.shine.leaf.server.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shine.leaf.api.service.SequenceService;
import com.shine.leaf.server.gererator.SnowFlake;
import com.shine.leaf.server.node.NodeBean;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/19 22:39
 * @desc 生成唯一序列
 */
@Service
public class SequenceServiceImpl implements SequenceService {

  private static Logger logger = LoggerFactory.getLogger(SequenceServiceImpl.class);

  @Autowired
  private NodeBean nodeBean;

  private static int NODE_DEFAULT = 1;

  @Override
  public long nextLong() {
    int node = NODE_DEFAULT;
    try {
      node = nodeBean.getNode();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    logger.info("node is : {}", node);
    SnowFlake snowFlake = new SnowFlake(node);
    return snowFlake.next();
  }

  @Override
  public String nextString() {
    return null;
  }

  @Override
  public String nextUUID() {
    return UUID.randomUUID().toString();
  }

}
