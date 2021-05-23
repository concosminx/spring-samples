package com.nimsoc.rr.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimsoc.rr.dao.CarDao;
import com.nimsoc.rr.model.Car;
import com.nimsoc.rr.service.CarService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarServiceImpl implements CarService {

  @Autowired
  CarDao carDao;

  @Override
  public Mono<Car> createCar(Car car) {
    return carDao.save(car);
  }

  @Override
  public Mono<Car> findOne(int id) {
    return carDao.findById(id);
  }

  @Override
  public Mono<Car> findByLicense(String license) {
    return carDao.findByLicensePlate(license);
  }

  @Override
  public Flux<Car> findAll() {
    return carDao.findAll();
  }

  @Override
  public Mono<Car> update(Car car) {
    return carDao.save(car);
  }

  @Override
  public Mono<Void> delete(int id) {
    return carDao.deleteById(id);
  }

  @Override
  public boolean findUserMapping(int userId) {
    return carDao.findByUserId(userId) != null;
  }

}
