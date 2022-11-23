package com.nimsoc.hazelcast.command;

import com.nimsoc.hazelcast.model.User;
import com.nimsoc.hazelcast.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class StartUp implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    List<User> all = userRepository.findAll();
    if (all.isEmpty()) {
      IntStream.range(1, 5).forEach(x -> {
        userRepository.save(new User(x, "user-" + x, "address-" + x));
      });
    }
  }
}
