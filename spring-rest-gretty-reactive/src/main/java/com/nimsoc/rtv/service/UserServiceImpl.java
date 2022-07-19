package com.nimsoc.rtv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimsoc.rtv.model.User;
import com.nimsoc.rtv.repository.UserRepo;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepo userRepo;

  @Override
  @Transactional
  public void createUser(User user) {
    userRepo.save(user).subscribe();
  }

  @Override
  public Mono<User> findOne(Integer id) {
    return userRepo.findById(id);
  }

  @Override
  public Flux<User> findAll() {
    return userRepo.findAll();
  }

  @Override
  public Mono<User> update(User user) {
    return userRepo.save(user);
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return userRepo.deleteById(id);
  }

  @Override
  public Flux<User> findByUserName(String userName) {
    return userRepo.findByUserName(userName);
  }

}
