package com.nimsoc.springrestclientexamples.services;

import com.nimsoc.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class ApiServiceImplTest {

  @Autowired
  ApiService apiService;

  @BeforeEach
  public void setUp() throws Exception {
  }

  @Disabled
  @Test
  public void testGetUsers() throws Exception {

    List<User> users = apiService.getUsers(3);

    assertEquals(4, users.size());
  }
}
