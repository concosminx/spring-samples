package com.nimsoc.wf.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nimsoc.wf.model.User;

import reactor.core.publisher.Flux;

public interface UserRepo extends ReactiveMongoRepository<User, Integer> {

  @Query("{ 'userName': ?0 }")
  Flux<User> findByUserName(final String userName);
}
