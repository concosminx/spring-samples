package com.example.sse.service.impl;

import com.example.sse.objects.OrderFood;
import com.example.sse.service.FoodService;
import org.springframework.stereotype.Service;

@Service
public class OrderFoodApplicationService implements FoodService {

  private final OrderFoodListener orderFoodListener;

  OrderFoodApplicationService(OrderFoodListener orderFoodListener) {
    this.orderFoodListener = orderFoodListener;
  }

  @Override
  public void order() {
    orderFoodListener.notifyAll(new OrderFood());
  }
}