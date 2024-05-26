package com.example.sse.observer.impl;

import com.example.sse.objects.FoodStatus;
import com.example.sse.objects.OrderFood;
import com.example.sse.observer.Observer;
import com.example.sse.service.EventService;

public class DeliveredObservable extends Observer {

  public DeliveredObservable(EventService eventService) {
    super(eventService);
  }

  @Override
  public void update(OrderFood orderFood) {

    if (orderFood.getStatus() == FoodStatus.ON_THE_WAY) {
      orderFood.setStatus(FoodStatus.DELIVERED);
      sendEvent(orderFood, "delivered");
    }
  }
}
