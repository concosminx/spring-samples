package com.nimsoc.email.runners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(name = "myapp.sometask", havingValue = "true")
public class ConditionalRunner implements CommandLineRunner , EnvironmentAware {

  private Environment environment;

  @Override
  public void run(String... args) throws Exception {
    log.info("Running from ConditionalRunner ....");
    log.info("environment.getActiveProfiles() :" + environment.getActiveProfiles());
    log.info("environment.getDefaultProfiles() :" + environment.getDefaultProfiles());

  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
