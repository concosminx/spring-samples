package com.nimsoc.sb.image.service;

import com.nimsoc.sb.image.dto.ImageUploadResponse;
import com.nimsoc.sb.image.model.ImageData;
import com.nimsoc.sb.image.repository.ImageDataRepository;
import com.nimsoc.sb.image.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

  @Autowired
  private ImageDataRepository imageDataRepository;

  public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {

    imageDataRepository.save(ImageData.builder()
        .name(file.getOriginalFilename())
        .type(file.getContentType())
        .imageData(ImageUtil.compressImage(file.getBytes())).build());

    return new ImageUploadResponse("Image uploaded successfully: " +
        file.getOriginalFilename());

  }

  @Transactional
  public ImageData getInfoByImageByName(String name) {
    Optional<ImageData> dbImage = imageDataRepository.findByName(name);

    return ImageData.builder()
        .name(dbImage.get().getName())
        .type(dbImage.get().getType())
        .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

  }

  @Transactional
  public byte[] getImage(String name) {
    Optional<ImageData> dbImage = imageDataRepository.findByName(name);
    byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
    return image;
  }


}
