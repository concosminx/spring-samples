package com.nimsoc.jks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:chuck-config.xml")
public class JksApplication {

  public static void main(String[] args) {
    SpringApplication.run(JksApplication.class, args);
  }

}
