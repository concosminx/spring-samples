package com.nimsoc.spring.mongodb.repository;

import com.nimsoc.spring.mongodb.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, Integer> {

  List<Author> findByName(String name);

  @Query("{'Address.city':?0}")
  List<Author> findByCity(String city);

}
