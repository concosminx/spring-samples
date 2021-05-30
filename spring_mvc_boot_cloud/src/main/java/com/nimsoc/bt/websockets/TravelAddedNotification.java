package com.nimsoc.bt.websockets;

public class TravelAddedNotification {

  private String name;

  public TravelAddedNotification() {
  }

  public TravelAddedNotification(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
