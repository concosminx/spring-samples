package com.nimsoc.redis.service;

import com.nimsoc.redis.model.Product;
import com.nimsoc.redis.request.CreateProductRequest;

import java.util.List;

public interface ProductService {

  List<Product> getAllProducts();

  Product getProductById(long productId);

  Product save(CreateProductRequest productRequest);

}