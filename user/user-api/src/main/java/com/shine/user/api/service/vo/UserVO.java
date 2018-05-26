package com.shine.user.api.service.vo;

import java.io.Serializable;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/26 23:41
 * @desc user的VO对象
 */
public class UserVO implements Serializable {

  private Long id;
  private String nickname;
  private String email;
  private String mobile;
  private String idCard;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }
}
