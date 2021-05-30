package com.nimsoc.pets.spc.repositories;

import com.nimsoc.pets.spc.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
