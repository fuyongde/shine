package com.shine.leaf.server.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/20 18:34
 * @desc zk的配置
 */
@Component
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperConfig {

  private String address;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
