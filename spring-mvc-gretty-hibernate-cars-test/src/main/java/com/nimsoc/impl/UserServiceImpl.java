package com.nimsoc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nimsoc.model.Car;
import com.nimsoc.model.User;
import com.nimsoc.service.UserService;

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
  public User findOne(int id) {
    return userDao.findOne(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<User> findAll() {
    return userDao.findAll();
  }

  @Transactional
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
    user.setCar(car);
    int remainingBal = user.getWallet() - car.getCost();
    user.setWallet(remainingBal);
    userDao.update(user);
  }

  @Override
  public boolean findCarMapping(String userName, int carId) {
    return userDao.findCarMapping(userName, carId);
  }

}
