package com.nimsoc.cloud.consumer.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "microservice-a")
@RibbonClient(name = "microservice-a")
public interface RandomServiceProxy {

  @RequestMapping(value = "/microservice-a/random", method = RequestMethod.GET)
  //@RequestMapping(value = "/random", method = RequestMethod.GET)
  public List<Integer> getRandomNumbers();
}
