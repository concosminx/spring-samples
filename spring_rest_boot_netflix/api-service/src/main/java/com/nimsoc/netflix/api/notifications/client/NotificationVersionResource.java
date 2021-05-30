package com.nimsoc.netflix.api.notifications.client;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("http://notification-service/v")
public interface NotificationVersionResource {

  @RequestMapping(value = "/version", method = GET)
  String version();

}
