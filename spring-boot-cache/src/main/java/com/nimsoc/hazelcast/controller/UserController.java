package com.nimsoc.hazelcast.controller;

import java.util.List;

import com.nimsoc.hazelcast.model.User;
import com.nimsoc.hazelcast.service.CachingService;
import com.nimsoc.hazelcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache-api")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping("/getAllUsers")
  public List<User> getAllUsers() {
    return service.getUsers();
  }

  @GetMapping("/getUserById/{id}")
  public User getUser(@PathVariable int id) {
    return service.getOneUser(id);
  }

  @DeleteMapping("/deleteUser/{id}")
  public String deleteUser(@PathVariable int id) {
    return service.deleteUser(id);
  }

  @Autowired
  CachingService cachingService;

  @GetMapping("/clearAllCaches")
  public void clearAllCaches() {
    cachingService.evictAllCaches();
  }

}