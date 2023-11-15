package com.nimsoc.sb.image.repository;

import com.nimsoc.sb.image.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
  Optional<ImageData> findByName(String name);
}
