package com.nimsoc.neo4j.api.repository;

import com.nimsoc.neo4j.api.node.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BookRepository extends Neo4jRepository<Book, String> {
  Book findByName(String name);
}
