package com.shine.user.server.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.shine.commons.api.BaseResponse;
import com.shine.commons.api.exception.ServiceException;
import com.shine.commons.utils.BeanMapper;
import com.shine.commons.utils.Cryptos;
import com.shine.commons.utils.Digests;
import com.shine.commons.utils.Encodes;
import com.shine.leaf.api.service.SequenceService;
import com.shine.user.server.domain.PasswordAuth;
import com.shine.user.server.repository.PasswordAuthMapper;
import com.shine.user.server.domain.User;
import com.shine.user.server.repository.UserMapper;
import com.shine.user.api.service.ao.UserAO;
import com.shine.user.api.service.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/26 16:33
 * @desc 用户的service
 */
@Service
public class UserService {

  private static final int SALT_SIZE = 8;
  private static final int HASH_ITERATIONS = 1024;

  @Resource private UserMapper userMapper;
  @Resource private PasswordAuthMapper passwordAuthMapper;
  @Resource private StringRedisTemplate stringRedisTemplate;

  @Reference private SequenceService sequenceService;

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
    passwordAuth.setPassword(encryptPassword(password, salt));
  }

  private String encryptPassword(String password, byte[] salt) {
    byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_ITERATIONS);
    return Encodes.encodeHex(hashPassword);
  }

  public UserVO getById(Long id) {
    User user = userMapper.getById(id);
    return BeanMapper.map(user, UserVO.class);
  }

  public String login(String username, String password) {
    User user =
        Optional.ofNullable(userMapper.getByUsername(username))
            .orElseThrow(() -> new ServiceException("用户不存在"));

    PasswordAuth passwordAuth =
        Optional.ofNullable(passwordAuthMapper.getByUserId(user.getId()))
            .orElseThrow(() -> new ServiceException("用户未设置密码"));

    String target = encryptPassword(password, Encodes.decodeHex(passwordAuth.getSalt()));
    if (!StringUtils.equals(target, passwordAuth.getPassword())) {
      throw new ServiceException("密码不匹配");
    }

    Map<String, Object> map = Maps.newTreeMap();
    map.put("username", username);
    map.put("userId", user.getId());
    map.put("type", user.getType());

    String tokenSource = JSON.toJSONString(map);
    String token =
        Encodes.encodeBase64(
            Cryptos.aesEncrypt(tokenSource.getBytes(), "fuyongdefuyongde".getBytes()));
    stringRedisTemplate.opsForValue().set("user.token." + user.getId(), token, 30, TimeUnit.DAYS);

    return token;
  }
}
