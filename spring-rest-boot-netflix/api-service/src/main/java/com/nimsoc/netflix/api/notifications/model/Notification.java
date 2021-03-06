package com.nimsoc.netflix.api.notifications.model;

import java.time.LocalDateTime;

public class Notification {

  private String id;
  private String message;

  public String getId() {
    return id;
  }

  public Notification setId(String id) {
    this.id = id;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public Notification setMessage(String message) {
    this.message = message;
    return this;
  }
}
