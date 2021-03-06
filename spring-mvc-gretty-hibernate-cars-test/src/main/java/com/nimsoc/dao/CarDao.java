package com.nimsoc.dao;

import java.util.List;

import com.nimsoc.model.Car;

public interface CarDao {

  Car save(Car car);

  void update(Car car);

  void delete(int id);

  Car findOne(int id);

  List<Car> findAll();
}
