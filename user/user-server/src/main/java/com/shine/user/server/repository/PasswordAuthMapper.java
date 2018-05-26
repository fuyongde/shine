package com.shine.user.server.repository;

import com.shine.user.server.domain.PasswordAuth;
import org.apache.ibatis.annotations.Param;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/26 23:16
 * @desc 密码认证
 */
public interface PasswordAuthMapper {

  int insertSelective(@Param("pojo") PasswordAuth passwordAuth);
}
