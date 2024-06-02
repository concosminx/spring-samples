package com.nimsoc.redis.controller;

import com.nimsoc.redis.mapper.ProductMapper;
import com.nimsoc.redis.model.Product;
import com.nimsoc.redis.request.CreateProductRequest;
import com.nimsoc.redis.response.CreateProductResponse;
import com.nimsoc.redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/all")
  List<Product> findAll() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  Product findById(@PathVariable long id) {
    return productService.getProductById(id);
  }

  @PostMapping("/create")
  public ResponseEntity<CreateProductResponse> save(@RequestBody CreateProductRequest productRequest) {
    CreateProductResponse productResponse = new CreateProductResponse();

    Product product = productService.save(productRequest);
    productResponse.setProductDto(ProductMapper.convertToDto(product));

    return ResponseEntity.ok(productResponse);
  }
}