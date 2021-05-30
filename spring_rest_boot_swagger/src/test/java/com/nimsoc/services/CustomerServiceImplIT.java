package com.nimsoc.services;

import com.nimsoc.api.v1.mapper.CustomerMapper;
import com.nimsoc.api.v1.model.CustomerDTO;
import com.nimsoc.bootstrap.Bootstrap;
import com.nimsoc.domain.Customer;
import com.nimsoc.repositories.CategoryRepository;
import com.nimsoc.repositories.CustomerRepository;
import com.nimsoc.repositories.VendorRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;

//import static junit.framework.TestCase.assertEquals;
//import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
//import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  VendorRepository vendorRepository;

  CustomerService customerService;

  @BeforeEach
  public void setUp() throws Exception {
    System.out.println("Loading Customer Data");
    System.out.println(customerRepository.findAll().size());

    //setup data for testing
    Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
    bootstrap.run(); //load data

    customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
  }

  @Test
  public void patchCustomerUpdateFirstName() throws Exception {
    String updatedName = "UpdatedName";
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);
    //save original first name
    String originalFirstName = originalCustomer.getFirstname();
    String originalLastName = originalCustomer.getLastname();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstname(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Customer updatedCustomer = customerRepository.findById(id).get();

    assertNotNull(updatedCustomer);
    assertEquals(updatedName, updatedCustomer.getFirstname());
    assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstname())));
    assertThat(originalLastName, equalTo(updatedCustomer.getLastname()));
  }

  @Test
  public void patchCustomerUpdateLastName() throws Exception {
    String updatedName = "UpdatedName";
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);

    //save original first/last name
    String originalFirstName = originalCustomer.getFirstname();
    String originalLastName = originalCustomer.getLastname();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setLastname(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Customer updatedCustomer = customerRepository.findById(id).get();

    assertNotNull(updatedCustomer);
    assertEquals(updatedName, updatedCustomer.getLastname());
    assertThat(originalFirstName, equalTo(updatedCustomer.getFirstname()));
    assertThat(originalLastName, not(equalTo(updatedCustomer.getLastname())));
  }

  private Long getCustomerIdValue() {
    List<Customer> customers = customerRepository.findAll();

    System.out.println("Customers Found: " + customers.size());

    //return first id
    return customers.get(0).getId();
  }
}
