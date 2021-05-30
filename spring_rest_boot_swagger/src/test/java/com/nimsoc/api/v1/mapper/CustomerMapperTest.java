package com.nimsoc.api.v1.mapper;

import com.nimsoc.api.v1.model.CustomerDTO;
import com.nimsoc.domain.Customer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
//import org.junit.Test;

//import static org.junit.Assert.assertEquals;
public class CustomerMapperTest {

  public static final String FIRSTNAME = "Jimmy";
  public static final String LASTNAME = "Fallon";
  CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  @Test
  public void customerToCustomerDTO() throws Exception {
    //given
    Customer customer = new Customer();
    customer.setFirstname(FIRSTNAME);
    customer.setLastname(LASTNAME);

    //when
    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

    //then
    assertEquals(FIRSTNAME, customerDTO.getFirstname());
    assertEquals(LASTNAME, customerDTO.getLastname());

  }

}
