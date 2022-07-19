package com.nimsoc.wf.service;

import com.nimsoc.wf.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<User> createUser(User user);

  Flux<User> findAll();

  Mono<User> update(User user);

  Mono<Void> delete(Integer id);

  Mono<User> findOne(Integer id);

  Flux<User> findByUserName(String userName);

}
