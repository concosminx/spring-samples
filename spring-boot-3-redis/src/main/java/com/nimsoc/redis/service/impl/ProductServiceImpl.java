package com.nimsoc.redis.service.impl;

import com.nimsoc.redis.mapper.ProductMapper;
import com.nimsoc.redis.model.Product;
import com.nimsoc.redis.repository.ProductRepository;
import com.nimsoc.redis.request.CreateProductRequest;
import com.nimsoc.redis.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepository productRepository;

  @Override
  @Cacheable(value = "product")
  public List<Product> getAllProducts() {
    getLongProcess();
    return productRepository.findAll();
  }

  @Override
  @Caching(evict = {@CacheEvict(value = "product", allEntries = true)})
  public Product save(CreateProductRequest productRequest) {
    Product product = ProductMapper.convertToProduct(productRequest);
    return productRepository.save(product);
  }

  @Override
  @Cacheable(value = "product", key = "#productId")
  public Product getProductById(long productId) {
    getLongProcess();
    Optional<Product> productOpt = productRepository.findById(productId);
    return productOpt.isPresent() ? productOpt.get() : new Product();
  }

  private void getLongProcess() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      log.error("Exception occured: " + e);
    }
  }
}