package com.cams.resource;

import com.cams.domain.models.Customer;
import com.cams.dto.CustomerDTO;
import com.cams.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerResource {

    @Autowired
    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<Customer> fetchAll() {
        return customerService.findAll();
    }

    @GetMapping(value = "/customer/{lastName}" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<Customer> fetchByLastName(@PathVariable String lastName) {
        return customerService.findByLastName(lastName);
    }

    @PostMapping(value = "/customer" , consumes = MediaType.APPLICATION_JSON_VALUE)
    void create(@Valid  @RequestBody CustomerDTO customerDTO){
        customerService.create(customerDTO);
    }

    @PutMapping(value = "/customer/{customerId}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    Customer update(@RequestBody CustomerDTO customerDTO, @PathVariable Long customerId){
        return customerService.update(customerDTO, customerId);
    }

}
