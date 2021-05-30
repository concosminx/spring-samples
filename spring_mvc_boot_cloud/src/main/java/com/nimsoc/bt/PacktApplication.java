package com.nimsoc.bt;

import com.nimsoc.bt.booking.BookingService;
import com.nimsoc.bt.model.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableHystrix
public class PacktApplication {

  @Autowired
  BookingService bookingService;

  public static void main(String[] args) {
    SpringApplication.run(PacktApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
