package com.nimsoc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimsoc.model.Car;
import com.nimsoc.service.CarService;

@Service
public class CarServiceImpl implements CarService {

  CarDaoImpl carDao;

  @Autowired
  public CarServiceImpl(CarDaoImpl carDao) {
    super();
    this.carDao = carDao;
  }

  public Car createCar(Car car) {
    return carDao.save(car);
  }

  public Car findOne(int id) {
    return carDao.findOne(id);
  }

  public Car findByLicense(String license) {
    return carDao.findByLicense(license);
  }

  public List<Car> findAll() {
    return carDao.findAll();
  }

  public void update(Car car) {
    carDao.update(car);
  }

  public void delete(int id) {
    carDao.delete(id);
  }

  @Override
  public boolean findUserMapping(int carId) {
    // TODO Auto-generated method stub
    return carDao.findUserMapping(carId);
  }

}
