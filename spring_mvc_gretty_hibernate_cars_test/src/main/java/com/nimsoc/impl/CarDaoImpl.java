package com.nimsoc.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nimsoc.dao.CarDao;
import com.nimsoc.model.Car;

@Repository
public class CarDaoImpl implements CarDao {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Car save(Car car) {
    em.persist(car);
    return findOne(em.createNativeQuery("select max(car_id) from car").getFirstResult());
  }

  @Override
  public Car findOne(int carId) {
    return em.find(Car.class, carId);
  }

  public Car findByLicense(String license) {
    Query q = em.createNamedQuery("Car.getCarByLicense");
    q.setParameter("licensePlate", license);
    return (Car) q.getResultList().get(0);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Car> findAll() {
    return em.createNamedQuery("Car.findAll").getResultList();
  }
@Override
  public void update(Car car) {
    em.merge(car);
  }

  @Override
  public void delete(int carId) {
    em.remove(em.find(Car.class, carId));
  }

  public boolean findUserMapping(int carId) {
    List list = em.createNativeQuery("select * from users where car_Id=" + carId).getResultList();
    return !list.isEmpty();
  }
}
