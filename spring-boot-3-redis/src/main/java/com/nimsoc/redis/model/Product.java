package com.nimsoc.redis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
@Accessors(chain = true)
public class Product implements Serializable {

  private static final long serialVersionUID = 7328527175502615992L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PRICE")
  private double price;

  @Column(name = "DESCRIPTION")
  private String description;

}