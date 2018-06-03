package com.shine.user.server.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/6/2 15:33
 * @desc 日志切面
 */
@Aspect
@Component
public class LogAspect {

  private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

  private final ThreadLocal<Long> serviceStartTime = new ThreadLocal<>();

  @Pointcut(value = "execution(public * com.shine.user.server.service..*.*(..))")
  public void serviceLog() {
  }

  @Before("serviceLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    serviceStartTime.set(System.currentTimeMillis());

    Object[] params = joinPoint.getArgs();

    // 记录下请求内容
    logger.info("******************service start******************");
    logger.info("method : {}", joinPoint.getSignature());
    if (Objects.nonNull(params) && params.length > 0) {
      logger.info("params : {}", params);
    }

  }

  @AfterReturning(returning = "result", pointcut = "serviceLog()")
  public void doAfterReturning(Object result) throws Throwable {
    // 处理完请求，返回内容
    logger.info("service result : {}", result);
    logger.info("service use : {}ms", (System.currentTimeMillis() - serviceStartTime.get()));
    logger.info("******************service end******************");
    serviceStartTime.remove();
  }

}
