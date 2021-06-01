package com.nimsoc.boot.roomwebapp;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

  private RoomServices roomServices;
  private final Counter roomsCounter;

  @Autowired
  public ApiController(RoomServices roomServices, MeterRegistry registry) {
    super();
    this.roomServices = roomServices;
    this.roomsCounter = registry.counter("services.room.invoke");
  }

  @GetMapping("/rooms")
  @Timed
  public List<Room> getAllRooms() {
    roomsCounter.increment();
    return this.roomServices.getAllRooms();
  }
  
  
}
