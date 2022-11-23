package com.nimsoc.barcode.controller;

import com.nimsoc.barcode.dto.CodeResponse;
import com.nimsoc.barcode.generators.ZXingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/barcode")
@Slf4j
public class ZXingController {

  @GetMapping(value = "/qr/{textContent}/{width}/{height}", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<byte[]> generateByteStream(@PathVariable(value = "textContent") String textContent,
                                                   @PathVariable(value = "width") int width,
                                                   @PathVariable(value = "height") int height
  ) {
    ResponseEntity<byte[]> re = null;

    if (0 >= width ||
        0 >= height ||
        textContent == null) {
      re = ResponseEntity.badRequest().build();
    } else {
      try {
        byte[] bytes = ZXingUtil.getQRCodeImage(textContent, width, height);
        re = ResponseEntity.ok(bytes);
      } catch (Exception exc) {
        log.error("Error generating QRCode", exc);
        re =  ResponseEntity.internalServerError().build();
      }
    }
    return re;
  }


  @PostMapping(value = "/qr")
  private CodeResponse readQR(@RequestParam("file") MultipartFile file) throws Exception {
    return ZXingUtil.readCode(file.getInputStream());
  }


}
