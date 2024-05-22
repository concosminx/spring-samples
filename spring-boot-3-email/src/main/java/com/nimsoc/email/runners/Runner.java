package com.nimsoc.email.runners;

import com.nimsoc.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  @Autowired
  private EmailService emailService;

  @Override
  public void run(String... args) throws Exception {
    emailService.sendSimpleMessage("johndoe@some.com", "hi", "welcome to out project");

    emailService.sendWelcomeEmail("johndoe@some.com", "John");
  }

}
