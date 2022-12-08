package com.nimsoc.neo4j.api.controller;

import com.nimsoc.neo4j.api.node.Customer;
import com.nimsoc.neo4j.api.node.Product;
import com.nimsoc.neo4j.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerProductController {
  @Autowired
  private CustomerRepository repository;

  @PostConstruct
  public void purchaseOrder() {
    List<Product> products = new ArrayList<>();
    products.add(new Product(123, "TV", 1, 180000));
    products.add(new Product(456, "Remote", 1, 30000));
    Customer customer = new Customer();
    customer.setCid(156);
    customer.setName("Zuckerman");
    customer.setAddress(new String[] { "Bucharest", "Victoriei" });
    customer.setProducts(products);
    repository.save(customer);
  }

  @GetMapping("/getOrders")
  public List<Customer> getPurchaseOrder() {
    return (List<Customer>) repository.findAll();
  }

  @GetMapping("/getInfo/{name}")
  public Customer getInfo(@PathVariable String name) {
    return repository.findByName(name);
  }
}
