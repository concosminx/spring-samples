package com.nimsoc.rtv.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nimsoc.rtv.model.User;

import reactor.core.publisher.Flux;

public interface UserRepo extends ReactiveMongoRepository<User, Integer> {

  @Query("{ 'userName': ?0 }")
  Flux<User> findByUserName(final String userName);
}
