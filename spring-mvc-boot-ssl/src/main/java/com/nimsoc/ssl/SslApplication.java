package com.nimsoc.ssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslApplication {

  public static void main(String[] args) {
    SpringApplication.run(SslApplication.class, args);
  }

  @RestController
  @RequestMapping("/api")
  public class ApiController {

    @GetMapping("/greeting")
    public String getGreeting() {
      return "Hello World from the API";
    }
  }
}
