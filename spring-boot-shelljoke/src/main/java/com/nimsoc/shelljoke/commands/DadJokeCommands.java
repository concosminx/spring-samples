package com.nimsoc.shelljoke.commands;


import com.nimsoc.shelljoke.client.DadJokeClient;
import com.nimsoc.shelljoke.model.DadJokeResponse;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DadJokeCommands {

  private final DadJokeClient dadJokeClient;

  public DadJokeCommands(DadJokeClient dadJokeClient) {
    this.dadJokeClient = dadJokeClient;
  }

  @ShellMethod(key = "random",value = "I will return a random dad joke!")
  public String getRandomDadJoke() {
    DadJokeResponse random = dadJokeClient.random();
    return random.joke();
  }

}
