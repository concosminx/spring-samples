package com.nimsoc.pets.spc.repositories;

import com.nimsoc.pets.spc.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
