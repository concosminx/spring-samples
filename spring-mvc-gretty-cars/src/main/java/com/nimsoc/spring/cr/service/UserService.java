package com.nimsoc.spring.cr.service;

import java.util.List;

import com.nimsoc.spring.cr.model.Car;
import com.nimsoc.spring.cr.model.User;

public interface UserService {

  User createUser(User user);

  User findOne(int user);

  List<User> findAll();

  void update(User user);

  void delete(int id);

  User findByUserName(String username);

  void selectCar(User user, Car car);

  boolean findCarMapping(int userId);
}
