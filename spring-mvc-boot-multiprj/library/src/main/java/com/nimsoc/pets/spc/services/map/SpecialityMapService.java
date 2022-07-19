package com.nimsoc.pets.spc.services.map;

import com.nimsoc.pets.spc.model.Speciality;
import com.nimsoc.pets.spc.services.SpecialtyService;
import org.springframework.stereotype.Service;

import java.util.Set;
import org.springframework.context.annotation.Profile;

@Service
@Profile({"default", "map"})

public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialtyService {

  @Override
  public Set<Speciality> findAll() {
    return super.findAll();
  }

  @Override
  public Speciality findById(Long id) {
    return super.findById(id);
  }

  @Override
  public Speciality save(Speciality object) {
    return super.save(object);
  }

  @Override
  public void delete(Speciality object) {
    super.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }
}
