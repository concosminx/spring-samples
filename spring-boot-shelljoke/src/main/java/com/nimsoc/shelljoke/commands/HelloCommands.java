package com.nimsoc.shelljoke.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class HelloCommands {

  @ShellMethod(key = "hello", value = "I will say Hello!")
  public String hello() {
    return "Hello world!";
  }

  @ShellMethod(key = "goodbye", value = "I will say Goodbye!")
  public String goodbye() {
    return "Goodbye!";
  }
}
