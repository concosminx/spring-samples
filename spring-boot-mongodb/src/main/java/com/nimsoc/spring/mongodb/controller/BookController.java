package com.nimsoc.spring.mongodb.controller;

import com.nimsoc.spring.mongodb.model.Book;
import com.nimsoc.spring.mongodb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @PostMapping("/addBook")
  public String addBook(@RequestBody Book book) {
    bookRepository.save(book);
    return "Saved book with id: " + book.getId();
  }

  @GetMapping("/findAllBooks")
  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  @GetMapping("/findAllBooks/{id}")
  public Optional<Book> getBook(@PathVariable int id) {
    return bookRepository.findById(id);
  }

  @DeleteMapping("/delete/{id}")
  public String delete(@PathVariable int id) {
    bookRepository.findById(id);
    return "Deleted book with id: " + id;
  }
}
