package com.example.sse.service.impl;

import com.example.sse.objects.OrderFood;
import com.example.sse.observer.Observer;
import com.example.sse.observer.impl.DeliveredObservable;
import com.example.sse.observer.impl.KitchenObservable;
import com.example.sse.observer.impl.OnTheWayObservable;
import com.example.sse.observer.impl.OrderObservable;
import com.example.sse.service.EventService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class OrderFoodListener {
  private final EventService eventService;

  private final Collection<Observer> observers;

  public OrderFoodListener(EventService eventService) {
    this.eventService = eventService;
    this.observers = List.of(
        new OrderObservable(eventService),
        new KitchenObservable(eventService),
        new OnTheWayObservable(eventService),
        new DeliveredObservable(eventService)
    );
  }

  public void notifyAll(OrderFood orderFood) {
    observers.forEach(foodObserver -> foodObserver.update(orderFood));
  }
}
