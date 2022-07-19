package com.nimsoc.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nimsoc.dao.UserDao;
import com.nimsoc.model.User;

@Repository
public class UserDaoImpl implements UserDao {

  @PersistenceContext
  private EntityManager em;

  @Override
  public User save(User user) {
    em.persist(user);
    return findOne(em.createNativeQuery("select max(userId) from users").getFirstResult());
  }

  @Override
  public User findOne(int userId) {
    return em.find(User.class, userId);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> findAll() {
    return em.createNamedQuery("Users.findAll").getResultList();
  }

  @Override
  public void update(User user) {
    em.merge(user);
  }

  @Override
  public void delete(int userId) {
    em.remove(em.find(User.class, userId));
  }

  public User findByUserName(String userName) {
    Query q = em.createNamedQuery("User.getUsersByName");
    q.setParameter("userName", userName);
    return (User) q.getResultList().get(0);
  }

  public boolean findCarMapping(String userName, int carId) {
    List<User> list = em.createNativeQuery("select * from users where car_Id=" + carId).getResultList();
    if (list.isEmpty()) {
      return false;
    } else {
      for (User u : list) {
        if (u.getUserName().equals(userName)) {
          return true;
        }
      }
      return false;
    }
  }
}
