package com.shine.commons.utils;

import org.apache.commons.lang3.StringUtils;

import static com.shine.commons.consts.EmailPattern.EMAIL_PATTERN;

/**
 * @author fuyongde
 * @desc 邮箱验证的工具类
 * @date 2017/11/9 19:32
 */
public class EmailChecker {

  /**
   * 是否为邮箱
   * @param email
   * @return
   */
  public static boolean isEmail(String email) {
    return StringUtils.isNotBlank(email) && email.matches(EMAIL_PATTERN);
  }
}
