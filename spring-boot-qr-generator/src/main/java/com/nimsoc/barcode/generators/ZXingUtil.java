package com.nimsoc.barcode.generators;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.nimsoc.barcode.dto.CodeResponse;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Slf4j
public class ZXingUtil {

  public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }

  public static CodeResponse readCode(InputStream is) {
    CodeResponse cr = null;
    try {
      BufferedImage bufferedImg = ImageIO.read(is);
      LuminanceSource source = new BufferedImageLuminanceSource(bufferedImg);
      BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
      Result result = new MultiFormatReader().decode(bitmap);
      cr = new CodeResponse(result.getBarcodeFormat().toString(), result.getText());
    } catch (NotFoundException |IOException e) {
      log.error("Error on code reading ", e);
    }
    return cr != null ? cr : new CodeResponse();
  }



}
