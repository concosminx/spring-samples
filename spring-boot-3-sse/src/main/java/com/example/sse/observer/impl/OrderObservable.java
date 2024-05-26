package com.example.sse.observer.impl;

import com.example.sse.objects.OrderFood;
import com.example.sse.observer.Observer;
import com.example.sse.service.EventService;

import static com.example.sse.objects.FoodStatus.ORDER_PLACED;

public class OrderObservable extends Observer {

  public OrderObservable(EventService eventService) {
    super(eventService);
  }

  @Override
  public void update(OrderFood orderFood) {

    if (orderFood.getStatus() == ORDER_PLACED) {

      sendEvent(orderFood, "order");
    }
  }
}