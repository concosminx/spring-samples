package com.nimsoc.spring.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimsoc.spring.sample.dao.UserDao;
import com.nimsoc.spring.sample.model.User;

@Service
public class UserServiceImpl implements UserService {

  UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    super();
    this.userDao = userDao;
  }

  @Override
  public void createUser(User user) {
    userDao.save(user);
  }

  @Override
  public User findOne(int id) {
    return userDao.findOne(id);
  }

  @Override
  public List<User> findAll() {
    return userDao.findAll();
  }

  @Override
  public void update(User user) {
    userDao.update(user);
  }

  @Override
  public void delete(int id) {
    userDao.delete(id);
  }

}
