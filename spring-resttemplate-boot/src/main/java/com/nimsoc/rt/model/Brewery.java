package com.nimsoc.rt.model;

import java.util.Date;

public class Brewery {

  public int id;
  public String obdb_id;
  public String name;
  public String brewery_type;
  public String street;
  public Object address_2;
  public Object address_3;
  public String city;
  public String state;
  public Object county_province;
  public String postal_code;
  public String country;
  public String longitude;
  public String latitude;
  public String phone;
  public String website_url;
  public Date updated_at;
  public Date created_at;

  @Override
  public String toString() {
    return "Brewery{" + "id=" + id + ", obdb_id=" + obdb_id + ", name=" + name + ", brewery_type=" + brewery_type + ", street=" + street + ", address_2=" + address_2 + ", address_3=" + address_3 + ", city=" + city + ", state=" + state + ", county_province=" + county_province + ", postal_code=" + postal_code + ", country=" + country + ", longitude=" + longitude + ", latitude=" + latitude + ", phone=" + phone + ", website_url=" + website_url + ", updated_at=" + updated_at + ", created_at=" + created_at + '}';
  }

}
