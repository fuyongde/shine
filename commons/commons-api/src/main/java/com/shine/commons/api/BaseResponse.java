package com.shine.commons.api;

import java.io.Serializable;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/6/2 16:06
 * @desc 基础响应
 */
public abstract class BaseResponse implements Serializable {

  public static final int NO_LOGIN = -1;

  public static final int SUCCESS = 0;

  public static final int FAIL = 1;

  public static final int NO_PERMISSION = 2;

  protected int code = SUCCESS;
  protected String msg = "success";

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
