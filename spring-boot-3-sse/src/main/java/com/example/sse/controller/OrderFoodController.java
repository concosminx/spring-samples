package com.example.sse.controller;

import com.example.sse.service.EventService;
import com.example.sse.service.FoodService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class OrderFoodController {
  private final EventService eventService;

  private final FoodService foodService;

  OrderFoodController(EventService eventService, FoodService foodService) {
    this.eventService = eventService;
    this.foodService = foodService;
  }

  @GetMapping(path = "/order-status", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  SseEmitter orderStatus() {
    return eventService.create();
  }

  @PostMapping(path = "/order-food", produces = MediaType.APPLICATION_JSON_VALUE)
  void orderFood() {

    foodService.order();
  }
}
