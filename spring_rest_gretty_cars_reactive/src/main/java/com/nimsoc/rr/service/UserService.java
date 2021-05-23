package com.nimsoc.rr.service;

import com.nimsoc.rr.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<User> createUser(User user);

  Mono<User> findOne(int user);

  Flux<User> findAll();

  Mono<User> update(User user);

  Mono<Void> delete(int id);

  Flux<User> findByUserName(String username);

  boolean findCarMapping(int userId);

}
