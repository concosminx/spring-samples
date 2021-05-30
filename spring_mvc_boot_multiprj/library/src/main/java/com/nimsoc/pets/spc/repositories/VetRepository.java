package com.nimsoc.pets.spc.repositories;

import com.nimsoc.pets.spc.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
