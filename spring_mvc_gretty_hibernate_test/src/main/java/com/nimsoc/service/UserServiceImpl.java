package com.nimsoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nimsoc.dao.UserDao;
import com.nimsoc.model.User;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDao userDao;

  @Override
  @Transactional
  public void createUser(User user) {
    userDao.save(user);
  }

  @Override
  @Transactional(readOnly = true)
  public User findOne(int id) {
    return userDao.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> findByUserName(String userName) {
    return userDao.findByUserName(userName);
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> findAll() {
    return userDao.findAll();
  }

  @Override
  @Transactional
  public void update(User user) {
    userDao.update(user);
  }

  @Override
  @Transactional
  public void delete(int id) {
    // TODO Auto-generated method stub
    userDao.delete(id);
  }

}
