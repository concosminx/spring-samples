package com.nimsoc.neo4j.api.repository;

import com.nimsoc.neo4j.api.node.Customer;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CustomerRepository extends Neo4jRepository<Customer, Integer> {

  Customer findByName(String name);

}
