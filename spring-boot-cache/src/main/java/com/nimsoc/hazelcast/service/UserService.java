package com.nimsoc.hazelcast.service;

import com.nimsoc.hazelcast.model.User;
import com.nimsoc.hazelcast.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

  @Autowired
  private UserRepository userRepository;

  //@Cacheable("users")
  public List<User> getUsers() {
    log.info("DB Hit - getUsers");
    return userRepository.findAll();
  }

  @Cacheable("users")
  public User getOneUser(int id) {
    log.info("DB Hit - getOneUser");
    return userRepository.findById(id).orElse(new User(-1, "dummy", "dummy"));
  }

  @CacheEvict(value="users", key = "#id")
  public String deleteUser(int id) {
    userRepository.deleteById(id);
    return "Delete user with id " + id;
  }
}
