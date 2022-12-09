package com.nimsoc.kafka.listeners;

import com.nimsoc.kafka.model.User;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "multiGroup", topics = "multitype")
public class MultiTypeKafkaListener {

  @KafkaHandler
  public void handleUser(User user) {
    System.out.println("User received: " + user);
  }

  @KafkaHandler(isDefault = true)
  public void unknown(Object object) {
    System.out.println("Unknown type received: " + object);
  }

}

