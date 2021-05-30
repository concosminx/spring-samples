package com.nimsoc.bt.security;

import com.nimsoc.bt.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

  @Autowired
  private TravelRepository travelRepository;

  @RequestMapping("/secret")
  public Long numberOfTravels() {
    return travelRepository.count();
  }
}
