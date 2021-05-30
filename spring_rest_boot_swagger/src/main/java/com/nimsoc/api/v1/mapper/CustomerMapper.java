package com.nimsoc.api.v1.mapper;

import com.nimsoc.api.v1.model.CustomerDTO;
import com.nimsoc.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  CustomerDTO customerToCustomerDTO(Customer customer);

  Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
