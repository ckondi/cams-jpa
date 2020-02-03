package com.cams.service;

import com.cams.domain.models.Customer;
import com.cams.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    List<Customer> findByLastName(String lastName);

    void create(CustomerDTO customerDTO);

    Customer update(CustomerDTO customerDTO, Long customerId);
}
