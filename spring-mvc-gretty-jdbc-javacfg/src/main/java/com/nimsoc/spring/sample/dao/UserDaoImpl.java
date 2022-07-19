package com.nimsoc.spring.sample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nimsoc.spring.sample.model.CustomRowMapper;
import com.nimsoc.spring.sample.model.User;

@Repository
public class UserDaoImpl implements UserDao {

  private final CustomRowMapper customRowMapper;

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserDaoImpl(CustomRowMapper customRowMapper, JdbcTemplate jdbcTemplate) {
    super();
    this.customRowMapper = customRowMapper;
    this.jdbcTemplate = jdbcTemplate;
    createTable();
  }

  @Override
  public void save(User user) {
    String queryUser = "insert into users (userName, phone) values (?,?)";
    jdbcTemplate.update(queryUser, new Object[]{user.getUserName(), user.getPhone()});
  }

  @Override
  public User findOne(int id) {
    String queryUser = "select * from users where userId=?";
    try {
      return (User) jdbcTemplate.queryForObject(queryUser, new Object[]{id}, customRowMapper);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public List<User> findAll() {
    createTable();
    String queryUser = "select * from users";
    List<User> users = jdbcTemplate.query(queryUser, new BeanPropertyRowMapper(User.class));
    return users;
  }

  @Override
  public void update(User user) {
    String queryUser = "update users set userName=?,phone=? where userId=?";
    jdbcTemplate.update(queryUser, new Object[]{user.getUserName(), user.getPhone(), user.getUserId()});
  }

  @Override
  public void delete(int id) {
    String queryUser = "delete from users where userId=?";
    jdbcTemplate.update(queryUser, new Object[]{id});
  }

  private void createTable() {
    String query = "create table IF NOT EXISTS users(userId SERIAL not null, username varchar(10) not null ,phone varchar(10) null, PRIMARY KEY (userId))";
    jdbcTemplate.execute(query);
  }
}
