package com.nimsoc.bt.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "travel-config")
public class BookingServiceSettings {

  private List<String> supportedDestinations;

  public List<String> getSupportedDestinations() {
    return supportedDestinations;
  }

  public void setSupportedDestinations(List<String> supportedDestinations) {
    this.supportedDestinations = supportedDestinations;
  }
}
