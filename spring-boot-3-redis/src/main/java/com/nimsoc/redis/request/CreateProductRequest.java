package com.nimsoc.redis.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateProductRequest {

  private String name;
  private double price;
  private String description;

}