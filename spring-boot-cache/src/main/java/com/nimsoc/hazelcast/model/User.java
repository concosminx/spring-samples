package com.nimsoc.hazelcast.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "USER_NONSTD")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  @Id
  private int id;
  private String name;
  private String address;
}
