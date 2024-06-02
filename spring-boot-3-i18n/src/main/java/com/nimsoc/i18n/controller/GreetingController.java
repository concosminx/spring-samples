package com.nimsoc.i18n.controller;

import com.nimsoc.i18n.aspect.RateLimited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class GreetingController {

  @Autowired
  private MessageSource messageSource;

  @RateLimited
  @GetMapping("/greeting")
  public String getGreeting(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
    return messageSource.getMessage("greeting.message", null, locale);
  }
}
