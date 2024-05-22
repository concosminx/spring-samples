package com.nimsoc.quartz.runners;

import com.nimsoc.quartz.service.EmailPromotionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialRunner implements CommandLineRunner {

  private final EmailPromotionService emailPromotionService;

  public InitialRunner(EmailPromotionService emailPromotionService) {
    this.emailPromotionService = emailPromotionService;
  }

  @Override
  public void run(String... args) throws Exception {
    emailPromotionService.scheduleEmailJobs();
  }
}
