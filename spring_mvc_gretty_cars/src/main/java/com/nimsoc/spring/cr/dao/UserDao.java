package com.nimsoc.spring.cr.dao;

import java.util.List;

import com.nimsoc.spring.cr.model.User;

public interface UserDao {

  User save(User user);

  void update(User user);

  void delete(int id);

  User findOne(int id);

  List<User> findAll();
}
