package com.nimsoc.spring.cr.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nimsoc.spring.cr.model.Car;
import com.nimsoc.spring.cr.model.User;
import com.nimsoc.spring.cr.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  UserDaoImpl userDao;

  @Autowired
  public UserServiceImpl(UserDaoImpl userDao) {
    super();
    this.userDao = userDao;
  }

  @Transactional
  @Override
  public User createUser(User user) {
    return userDao.save(user);
  }

  @Transactional(readOnly = true)
  @Override
  public User findOne(int id) {
    return userDao.findOne(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<User> findAll() {
    return userDao.findAll();
  }

  @Transactional
  @Override
  public void update(User user) {
    userDao.update(user);
  }

  @Transactional
  @Override
  public void delete(int id) {
    userDao.delete(id);
  }

  @Transactional(readOnly = true)
  @Override
  public User findByUserName(String username) {
    return userDao.findByUserName(username);
  }

  @Transactional
  @Override
  public void selectCar(User user, Car car) {
    userDao.selectCar(user.getUserId(), car.getCarId());
    int remainingBal = user.getWallet() - car.getCost();
    user.setWallet(remainingBal);
    userDao.update(user);
  }

  @Override
  public boolean findCarMapping(int userId) {
    return userDao.findCarMapping(userId);
  }

}
