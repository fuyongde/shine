package com.shine.user.server.aspect;

import com.shine.commons.api.BaseResponse;
import com.shine.commons.api.SingleResponse;
import com.shine.commons.api.exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/6/2 15:35
 * @desc Controller切面
 */
@Aspect
@Component
public class ControllerAspect {

  private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

  private final ThreadLocal<Long> startTime = new ThreadLocal<>();

  @Pointcut(value = "execution(public * com.shine.user.server.rest..*.*(..))")
  public void controller() {
  }

  @Around("controller()")
  public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) {
    logger.info("=============================Controller Start====================================");
    Object[] params = joinPoint.getArgs();
    if (Objects.nonNull(params) && params.length > 0) {
      logger.info("CONTROLLER REQUEST : {}", params);
    }
    startTime.set(System.currentTimeMillis());
    Object result;
    try {
      result = joinPoint.proceed();
    } catch (Throwable throwable) {
      result = handlerException(throwable);
    }
    logger.info("CONTROLLER RESPONSE : {}", result);
    logger.info("CONTROLLER SPEND TIME : {}ms", (System.currentTimeMillis() - startTime.get()));
    startTime.remove();
    logger.info("=============================Controller End====================================");
    return result;
  }

  /**
   * 封装异常信息，注意区分已知异常（自己抛出的）和未知异常
   */
  private SingleResponse<?> handlerException(Throwable throwable) {
    SingleResponse<?> result = new SingleResponse(throwable);

    // 已知异常
    if (throwable instanceof ServiceException) {
      result.setMsg(throwable.getMessage());
    }

    return result;
  }
}
