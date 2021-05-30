package com.nimsoc.pets.spc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "specialties")
public class Speciality extends BaseEntity {

  @Column(name = "description")
  private String description;

//  public Speciality(String description, Long id) {
//    super(id);
//    this.description = description;
//  }

//  public Speciality() {
//  }
  
//  public String getDescription() {
//    return description;
//  }
//
//  public void setDescription(String description) {
//    this.description = description;
//  }

}
