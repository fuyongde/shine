package com.shine.user.server.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shine.commons.utils.BeanMapper;
import com.shine.commons.utils.Digests;
import com.shine.commons.utils.Encodes;
import com.shine.leaf.api.service.SequenceService;
import com.shine.user.server.domain.PasswordAuth;
import com.shine.user.server.repository.PasswordAuthMapper;
import com.shine.user.server.domain.User;
import com.shine.user.server.repository.UserMapper;
import com.shine.user.api.service.ao.UserAO;
import com.shine.user.api.service.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/26 16:33
 * @desc 用户的service
 */
@Service
public class UserService {

  private final static int SALT_SIZE = 8;
  private final static int HASH_ITERATIONS = 1024;

  @Resource
  private UserMapper userMapper;
  @Resource
  private PasswordAuthMapper passwordAuthMapper;

  @Reference
  private SequenceService sequenceService;

  @Transactional
  public void register(User user) {
    user.setId(sequenceService.nextLong());
    userMapper.insertSelective(user);
  }

  @Transactional
  public long registerByPassword(UserAO userAO) {
    long userId = sequenceService.nextLong();
    User user = BeanMapper.map(userAO, User.class);
    user.setId(userId);
    user.setType(User.Type.CUSTOMER);
    userMapper.insertSelective(user);
    PasswordAuth passwordAuth = new PasswordAuth();
    passwordAuth.setId(userId);
    passwordAuth.setUserId(user.getId());
    encryptPassword(passwordAuth, userAO.getPassword());
    passwordAuthMapper.insertSelective(passwordAuth);
    return userId;
  }

  private void encryptPassword(PasswordAuth passwordAuth, String password) {
    byte[] salt = Digests.generateSalt(SALT_SIZE);
    passwordAuth.setSalt(Encodes.encodeHex(salt));
    byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_ITERATIONS);
    passwordAuth.setPassword(Encodes.encodeHex(hashPassword));
  }

  public UserVO getById(Long id) {
    User user = userMapper.getById(id);
    return BeanMapper.map(user, UserVO.class);
  }
}
