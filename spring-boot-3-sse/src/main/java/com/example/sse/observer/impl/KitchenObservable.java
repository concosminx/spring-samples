package com.example.sse.observer.impl;

import com.example.sse.objects.FoodStatus;
import com.example.sse.objects.OrderFood;
import com.example.sse.observer.Observer;
import com.example.sse.service.EventService;

public class KitchenObservable extends Observer {

  public KitchenObservable(EventService eventService) {
    super(eventService);
  }

  @Override
  public void update(OrderFood orderFood) {

    if (orderFood.getStatus() == FoodStatus.ORDER_PLACED) {

      orderFood.setStatus(FoodStatus.IN_THE_KITCHEN);
      sendEvent(orderFood, "kitchen");
      waitForProcess();
    }
  }
}