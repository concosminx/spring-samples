package com.nimsoc.rr.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nimsoc.rr.dao.UserDao;
import com.nimsoc.rr.model.User;
import com.nimsoc.rr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

  private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  UserDao userDao;

  @Transactional
  @Override
  public Mono<User> createUser(User user) {
    Mono<User> save = userDao.save(user);
    return save;
  }

  @Transactional(readOnly = true)
  @Override
  public Mono<User> findOne(int id) {
    return userDao.findById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public Flux<User> findAll() {
    return userDao.findAll();
  }

  @Transactional
  @Override
  public Mono<User> update(User user) {
    return userDao.save(user);
  }

  @Transactional
  @Override
  public Mono<Void> delete(int id) {
    return userDao.deleteById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public Flux<User> findByUserName(String username) {
    Flux<User> findByUserName = userDao.findByUserName(username);
    return findByUserName;
  }

  @Override
  public boolean findCarMapping(int carId) {
    return userDao.findByCarId(carId) != null;
  }

}
