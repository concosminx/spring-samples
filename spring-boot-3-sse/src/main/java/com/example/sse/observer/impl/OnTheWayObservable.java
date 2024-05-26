package com.example.sse.observer.impl;

import com.example.sse.objects.OrderFood;
import com.example.sse.observer.Observer;
import com.example.sse.service.EventService;

import static com.example.sse.objects.FoodStatus.IN_THE_KITCHEN;
import static com.example.sse.objects.FoodStatus.ON_THE_WAY;

public class OnTheWayObservable extends Observer {

  public OnTheWayObservable(EventService eventService) {
    super(eventService);
  }

  @Override
  public void update(OrderFood orderFood) {

    if (orderFood.getStatus() == IN_THE_KITCHEN) {

      orderFood.setStatus(ON_THE_WAY);
      sendEvent(orderFood, "on-the-way");
      waitForProcess();
    }
  }
}
