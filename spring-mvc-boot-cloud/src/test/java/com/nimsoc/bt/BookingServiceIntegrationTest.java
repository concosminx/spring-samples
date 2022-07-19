package com.nimsoc.bt;

import com.nimsoc.bt.booking.BookingService;
import com.nimsoc.bt.model.Travel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceIntegrationTest {

  @Autowired
  BookingService bookingService;

  @Test
  public void shouldBookATravel() {
    bookingService.book(new Travel("user_me", "LONDON", "PARIS"));
  }

}
