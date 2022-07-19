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
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

  @Builder
  public PetType(Long id, String name) {
    super(id);
    this.name = name;
  }

//  public PetType() {
//  }
  
  @Column(name = "name")
  private String name;

//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
@Override
    public String toString() {
        return name;
    }
  
}
