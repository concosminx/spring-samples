package com.nimsoc.quartz.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recipient {

  private String userId;
  private String name;
  private String emailAddress;

}
