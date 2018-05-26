package com.shine.user.server;

import java.util.concurrent.CountDownLatch;
import org.apache.commons.lang3.SystemUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/19 23:08
 * @desc 全局唯一id生成服务
 */
@SpringBootApplication
@MapperScan("com.shine.user.server.repository")
public class UserServerApplication {

  public static void main(String[] args) throws InterruptedException {
    if (SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_MAC) {
      System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
    }
    ApplicationContext ctx = new SpringApplicationBuilder()
        .sources(UserServerApplication.class)
        .listeners(new ApplicationPidFileWriter())
        .run(args);

    CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
    closeLatch.await();
  }

  @Bean
  public CountDownLatch countDownLatch() {
    return new CountDownLatch(1);
  }
}
