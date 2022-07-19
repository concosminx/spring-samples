package com.nimsoc.spring.sample.service;

import java.util.List;

import com.nimsoc.spring.sample.model.User;

public interface UserService {

  void createUser(User user);

  User findOne(int user);

  List<User> findAll();

  void update(User user);

  void delete(int id);
}
