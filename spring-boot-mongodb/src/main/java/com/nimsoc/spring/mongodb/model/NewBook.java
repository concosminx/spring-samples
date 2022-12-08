package com.nimsoc.spring.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewBook {
  private String name;
  private int quantity;
  private int price;
}
