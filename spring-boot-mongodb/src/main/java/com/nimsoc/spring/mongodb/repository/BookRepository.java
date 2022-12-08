package com.nimsoc.spring.mongodb.repository;

import com.nimsoc.spring.mongodb.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Integer> {

}
