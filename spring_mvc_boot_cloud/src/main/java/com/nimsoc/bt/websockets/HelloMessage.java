package com.nimsoc.bt.websockets;

public class HelloMessage {

  private String content;

  public HelloMessage() {
  }

  public HelloMessage(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

}
