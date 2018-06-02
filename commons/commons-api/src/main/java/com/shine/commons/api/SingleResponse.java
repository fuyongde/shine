package com.shine.commons.api;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/6/2 16:59
 * @desc 简单的响应
 */
public class SingleResponse<T> extends BaseResponse {
  private T data;

  public SingleResponse() {
  }

  public SingleResponse(T data) {
    this.data = data;
  }

  public SingleResponse(Throwable throwable) {
    super.code=FAIL;
    super.msg=throwable.toString();
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
