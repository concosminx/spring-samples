package com.nimsoc.services;

import com.nimsoc.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

  List<CategoryDTO> getAllCategories();

  CategoryDTO getCategoryByName(String name);
}
