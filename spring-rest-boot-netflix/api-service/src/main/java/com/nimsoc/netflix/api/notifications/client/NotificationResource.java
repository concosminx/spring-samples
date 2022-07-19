package com.nimsoc.netflix.api.notifications.client;

import com.nimsoc.netflix.api.notifications.model.Notification;
import com.netflix.hystrix.HystrixObservable;

import org.springframework.web.bind.annotation.RequestMapping;
import rx.Observable;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "http://notification-service", fallback = NotificationResourceImpl.class)
public interface NotificationResource {

  @RequestMapping(value = "/notifications", method = GET)
  List<Notification> findAll();

}
