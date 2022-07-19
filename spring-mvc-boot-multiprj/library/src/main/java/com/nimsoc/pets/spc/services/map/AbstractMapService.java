package com.nimsoc.pets.spc.services.map;

import com.nimsoc.pets.spc.model.BaseEntity;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class AbstractMapService<T extends BaseEntity, ID extends Long> {

  protected Map<Long, T> map = new HashMap<>();

  public Set<T> findAll() {
    return new HashSet<>(map.values());
  }

  public T findById(ID id) {
    return map.get(id);
  }

  public T save(T object) {
    if (object != null) {
      if (object.getId() == null) {
        object.setId(getNextId());
      }

      map.put(object.getId(), object);
    } else {
      throw new RuntimeException("Object cannot be null");
    }

    return object;
  }

  public void delete(T object) {
    map.entrySet().removeIf(entry -> entry.getValue().equals(object));
  }

  public void deleteById(ID id) {
    map.remove(id);
  }

  private Long getNextId() {

    Long nextId = null;

    try {
      nextId = Collections.max(map.keySet()) + 1;
    } catch (NoSuchElementException e) {
      nextId = 1L;
    }

    return nextId;
  }

}
