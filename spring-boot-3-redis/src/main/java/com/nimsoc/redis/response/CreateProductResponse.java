package com.nimsoc.redis.response;

import com.nimsoc.redis.dto.ProductDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateProductResponse {

  private ProductDto productDto;

}