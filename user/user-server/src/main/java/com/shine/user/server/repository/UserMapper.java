package com.shine.user.server.repository;

import com.shine.user.server.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/26 15:44
 * @desc 用户Mapper
 */
public interface UserMapper {

  int insertSelective(@Param("pojo") User user);

  int insertList(@Param("pojos") List<User> users);

  int update(@Param("pojo") User user);

  User getById(@Param("id") Long id);
}
