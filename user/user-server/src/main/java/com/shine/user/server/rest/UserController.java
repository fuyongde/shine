package com.shine.user.server.rest;

import com.shine.user.api.service.ao.UserAO;
import com.shine.user.api.service.vo.UserVO;
import com.shine.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/26 16:05
 * @desc 用户Controller
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<?> register(@RequestBody UserAO userAO, UriComponentsBuilder uriBuilder) {
    long id = userService.registerByPassword(userAO);
    URI uri = uriBuilder.path("/api/v1/task/" + id).build().toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uri);
    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public UserVO getById(@PathVariable Long id) {
    return userService.getById(id);
  }
}
