package com.shine.user.api.service.ao;

import java.io.Serializable;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/26 23:32
 * @desc User的AO对象
 */
public class UserAO implements Serializable {
  private String username;
  private String nickname;
  private String password;
  private String plainPassword;
  private String email;
  private String mobile;
  private String idCard;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPlainPassword() {
    return plainPassword;
  }

  public void setPlainPassword(String plainPassword) {
    this.plainPassword = plainPassword;
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
