package com.nimsoc.social.repository;

import com.nimsoc.social.model.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ProductRepository extends ListCrudRepository<Product, UUID> {
}
