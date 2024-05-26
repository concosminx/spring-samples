package com.example.minio.controller;

import com.example.minio.config.MinioConfig;
import com.example.minio.util.MinioUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
@RestController
@RequestMapping("/minio")
public class MinioController {
  @Autowired
  private MinioUtils minioUtils;

  @Autowired
  private MinioConfig minioConfig;


  @PostMapping("/upload")
  public String upload(@RequestParam("file") MultipartFile file) {
    try {
      String fileName = file.getOriginalFilename();
      String newFileName = System.currentTimeMillis() + "." + StringUtils.substringAfterLast(fileName, ".");
      String contentType = file.getContentType();
      minioUtils.uploadFile(minioConfig.getBucketName(), file, newFileName, contentType);
      return "upload success";
    } catch (Exception e) {
      log.error("upload fail", e);
      return "upload fail";
    }
  }

  @DeleteMapping("/")
  public void delete(@RequestParam("fileName") String fileName) {
    minioUtils.removeFile(minioConfig.getBucketName(), fileName);
  }

  @GetMapping("/info")
  public String getFileStatusInfo(@RequestParam("fileName") String fileName) {
    return minioUtils.getFileStatusInfo(minioConfig.getBucketName(), fileName);
  }

  @GetMapping("/url")
  public String getPresignedObjectUrl(@RequestParam("fileName") String fileName) {
    return minioUtils.getPresignedObjectUrl(minioConfig.getBucketName(), fileName);
  }

  @GetMapping("/download")
  public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) {
    try {
      InputStream fileInputStream = minioUtils.getObject(minioConfig.getBucketName(), fileName);
      response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
      response.setContentType("application/force-download");
      response.setCharacterEncoding("UTF-8");
      IOUtils.copy(fileInputStream, response.getOutputStream());
    } catch (Exception e) {
      log.error("download fail");
    }
  }
}
