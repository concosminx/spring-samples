package com.nimsoc.spring.mongodb.controller;

import java.util.List;

import com.nimsoc.spring.mongodb.model.Author;
import com.nimsoc.spring.mongodb.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author-service")
public class AuthorController {

  @Autowired
  private AuthorRepository repository;

  @PostMapping("/saveAuthor")
  public String saveAuthor(@RequestBody Author user) {
    repository.save(user);
    return "Author placed successfully...";
  }

  @GetMapping("/getAuthorByName/{name}")
  public List<Author> getAuthorByName(@PathVariable String name) {
    return repository.findByName(name);
  }

  @GetMapping("/getAuthorByAddress/{city}")
  public List<Author> getAuthorByAddress(@PathVariable String city) {
    return repository.findByCity(city);
  }

}
