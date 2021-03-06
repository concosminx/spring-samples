package com.nimsoc.spring.sample.dao;

import java.util.List;

import com.nimsoc.spring.sample.model.User;

public interface UserDao {

  void save(User user);

  void update(User user);

  void delete(int id);

  User findOne(int id);

  List<User> findAll();
}
