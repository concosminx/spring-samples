package com.nimsoc.jks.config;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  ChuckNorrisQuotes chuckNorrisQuotes() {
    return new ChuckNorrisQuotes();
  }

}
