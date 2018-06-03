package com.shine.commons.api.exception;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/6/3 18:15
 * @desc 服务异常
 */
public class ServiceException extends RuntimeException {

  public int code;

  public ServiceException(int code, String message) {
    super(message);
    this.code = code;
  }

  public ServiceException(String message) {
    super(message);
  }
}
