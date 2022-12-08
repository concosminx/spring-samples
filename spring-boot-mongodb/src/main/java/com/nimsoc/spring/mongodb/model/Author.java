package com.nimsoc.spring.mongodb.model;


import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "Books_DB")
public class Author {

  @Id
  private int id;
  private String name;
  private String gender;
  private List<NewBook> books;
  private Address address;
}
