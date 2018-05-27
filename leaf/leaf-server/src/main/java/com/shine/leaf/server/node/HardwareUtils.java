package com.shine.leaf.server.node;

import com.shine.commons.utils.Exceptions;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/** @author fuyongde 获取硬件信息 */
public class HardwareUtils {

  private static final Logger logger = LoggerFactory.getLogger(HardwareUtils.class);

  public static List<String> getMacAddress() {
    // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
    List<String> macAddressList = Lists.newArrayList();
    try {
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface network = networkInterfaces.nextElement();

        byte[] mac = network.getHardwareAddress();
        if (mac != null) {
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
          }

          String macAddress = sb.toString();
          if (StringUtils.isNotBlank(macAddress)) {
            macAddressList.add(macAddress);
            logger.info("Address = "+ sb.toString() + " @ [" + network.getName() + "] " + network.getDisplayName());
          }
        }
      }
    } catch (SocketException e) {
      throw Exceptions.unchecked(e);
    }

    macAddressList.sort(String::compareTo);

    return macAddressList;
  }
}
