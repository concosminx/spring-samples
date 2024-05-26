package com.example.sse.observer;

import com.example.sse.objects.OrderFood;
import com.example.sse.service.EventService;

public abstract class Observer {

  private final EventService eventService;

  protected Observer(EventService eventService) {
    this.eventService = eventService;
  }

  protected void waitForProcess() {

    try {
      Thread.sleep(2000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  protected void sendEvent(OrderFood orderFood, String eventName) {
    eventService.sendEvent(orderFood, eventName);
  }

  public abstract void update(OrderFood orderFood);
}
