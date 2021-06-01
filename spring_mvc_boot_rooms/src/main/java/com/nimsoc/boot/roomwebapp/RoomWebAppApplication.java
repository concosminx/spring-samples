package com.nimsoc.boot.roomwebapp;

import io.micrometer.core.instrument.Meter.Type;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RoomWebAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(RoomWebAppApplication.class, args);
  }

  @Bean
  MeterRegistryCustomizer<MeterRegistry> addPersonRegistry() {
    return registry -> registry.config().namingConvention().name("services.room.invoke", Type.COUNTER);
  }
}
