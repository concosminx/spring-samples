package com.nimsoc.neo4j.api.repository;

import com.nimsoc.neo4j.api.node.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProductRepository extends Neo4jRepository<Product, Integer> {

}
