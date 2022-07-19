package com.nimsoc.pets.spc.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "vets")
public class Vet extends Person {

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
          inverseJoinColumns = @JoinColumn(name = "speciality_id"))
  @Builder.Default
  private Set<Speciality> specialities = new HashSet<>();

//  public Vet(Long id, String firstName, String lastName) {
//    super(id, firstName, lastName);
//  }
//
//  public Vet() {
//  }
  
//  public Set<Speciality> getSpecialities() {
//    return specialities;
//  }
//
//  public void setSpecialities(Set<Speciality> specialities) {
//    this.specialities = specialities;
//  }

}
