package com.nimsoc.web;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.nimsoc.model.User;
import com.nimsoc.service.UserServiceImpl;

@RestController
public class HomeController {

  @Autowired
  private UserServiceImpl service;

  @RequestMapping(value = {"/create", "/"}, method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public ResponseEntity<?> create(@RequestBody User user, UriComponentsBuilder uCB) {
    service.createUser(user);
    UriComponents uc = uCB.path("/{id}").buildAndExpand(user.getUserId());
    return ResponseEntity.created(uc.toUri()).build();
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public void update(@RequestBody User user) {
    service.update(user);
  }

  @RequestMapping(value = "/patch/{id}", method = RequestMethod.PATCH)
  @ResponseStatus(HttpStatus.OK)
  public void patch(@PathVariable("id") int userId, @RequestBody Map<String, String> fields) {
    User user = service.findOne(userId);
    fields.forEach((k, v) -> {
      Field field = ReflectionUtils.findField(User.class, k);
      ReflectionUtils.setField(field, user, v);
    });
    service.update(user);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<User> findOne(@PathVariable("id") int userId) {
    User user = service.findOne(userId);
    HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<User>(user, status);
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<User> findAll() {
    return service.findAll();
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") int id) {
    service.delete(id);
  }

}
