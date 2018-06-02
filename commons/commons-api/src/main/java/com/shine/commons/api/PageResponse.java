package com.shine.commons.api;

import java.util.List;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/6/2 17:01
 * @desc 分页对象
 */
public class PageResponse<T> extends BaseResponse {

  private List<?> rows;
  private boolean hasMore;

  public PageResponse(List<?> rows, boolean hasMore) {
    this.rows = rows;
    this.hasMore = hasMore;
  }

  public List<?> getRows() {
    return rows;
  }

  public void setRows(List<?> rows) {
    this.rows = rows;
  }

  public boolean isHasMore() {
    return hasMore;
  }

  public void setHasMore(boolean hasMore) {
    this.hasMore = hasMore;
  }
}
