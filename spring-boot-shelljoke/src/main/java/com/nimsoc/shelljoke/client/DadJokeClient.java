package com.nimsoc.shelljoke.client;


import com.nimsoc.shelljoke.model.DadJokeResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface DadJokeClient {

  @GetExchange("/")
  DadJokeResponse random();
}
