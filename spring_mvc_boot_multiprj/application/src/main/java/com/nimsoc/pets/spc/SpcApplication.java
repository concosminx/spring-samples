package com.nimsoc.pets.spc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.something", "com.somethingelse"}) uses packages as base pckg scans *
public class SpcApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpcApplication.class, args);
  }

}
