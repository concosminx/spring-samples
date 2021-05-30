package com.nimsoc.netflix.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {

  public static void main(String[] args) {
    SpringApplication eurekaServer = new SpringApplication(EurekaServer.class);
    eurekaServer.addListeners(new ApplicationPidFileWriter("eureka-server.pid"));
    eurekaServer.run(args);
  }

}
