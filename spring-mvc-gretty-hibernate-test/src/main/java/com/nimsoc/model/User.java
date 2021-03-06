package com.nimsoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.UniqueConstraint;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "userx", uniqueConstraints = @UniqueConstraint(columnNames = {"USER_NAME"}))
@Component
@NamedQueries({ //In case of multiple named queries
  
  @NamedQuery(name = "User.getUsersByName", query = "SELECT u FROM User u where u.userName=:userName")
})
@NamedNativeQuery(
        name = "User.findAll", query = "select * from userx", resultClass = User.class
)
public class User {

  @Column(name = "USER_NAME")
  @Size(max = 20, min = 3, message = "Name length allowed: Max=20, Min=3")
  @NotEmpty(message = "Please Enter your name")
  String userName;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  int userId;

  @Pattern(regexp = "(^$|[0-9]{10})")
  @NotEmpty(message = "Please number can't be empty.")
  String phone;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
