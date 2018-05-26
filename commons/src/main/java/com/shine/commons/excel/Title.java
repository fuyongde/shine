package com.shine.commons.excel;

/**
 * @author fuyongde
 * @desc Excel的标题
 * @date 2017/11/29 18:13
 */
public class Title {

  private String titleKey;
  private String titleName;

  public Title() {
  }

  public Title(String titleKey, String titleName) {
    this.titleKey = titleKey;
    this.titleName = titleName;
  }

  public String getTitleKey() {
    return titleKey;
  }

  public void setTitleKey(String titleKey) {
    this.titleKey = titleKey;
  }

  public String getTitleName() {
    return titleName;
  }

  public void setTitleName(String titleName) {
    this.titleName = titleName;
  }

  @Override
  public String toString() {
    return "Title{" +
        "titleKey='" + titleKey + '\'' +
        ", titleName='" + titleName + '\'' +
        '}';
  }
}
