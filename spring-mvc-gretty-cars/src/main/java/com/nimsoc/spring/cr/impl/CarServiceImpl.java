package com.nimsoc.spring.cr.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimsoc.spring.cr.model.Car;
import com.nimsoc.spring.cr.service.CarService;

@Service
public class CarServiceImpl implements CarService {

  CarDaoImpl carDao;

  @Autowired
  public CarServiceImpl(CarDaoImpl carDao) {
    super();
    this.carDao = carDao;
  }

  @Override
  public Car createCar(Car car) {
    return carDao.save(car);
  }

  @Override
  public Car findOne(int id) {
    return carDao.findOne(id);
  }

  @Override
  public Car findByLicense(String license) {
    return carDao.findByLicense(license);
  }

  @Override
  public List<Car> findAll() {
    return carDao.findAll();
  }

  @Override
  public void update(Car car) {
    carDao.update(car);
  }

  @Override
  public void delete(int id) {
    carDao.delete(id);
  }

  @Override
  public boolean findUserMapping(int carId) {
    // TODO Auto-generated method stub
    return carDao.findUserMapping(carId);
  }

}
