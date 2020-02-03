package com.cams.service.impl;

import com.cams.domain.models.Customer;
import com.cams.dto.ContactDetailsDTO;
import com.cams.dto.CustomerDTO;
import com.cams.dto.converters.ContactDetailsConverter;
import com.cams.dto.converters.CustomerConverter;
import com.cams.repository.CustomerRepository;
import com.cams.service.CustomerService;
import com.cams.validation.ContactDetailsValidator;
import com.cams.validation.model.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final ContactDetailsValidator contactDetailsValidator;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private ContactDetailsConverter contactDetailsConverter;
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(ContactDetailsValidator contactDetailsValidator, CustomerConverter customerConverter, ContactDetailsConverter contactDetailsConverter, CustomerRepository customerRepository) {
        this.contactDetailsValidator = contactDetailsValidator;
        this.customerConverter = customerConverter;
        this.contactDetailsConverter = contactDetailsConverter;
        this.customerRepository = customerRepository;

    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @Override
    public void create(CustomerDTO customerDTO) {
        invokeValidationForContactList(customerDTO.getContactDetailsDTOS());
        Customer customer = customerConverter.convertDTOToDomain(customerDTO);
        customerRepository.save(customer);
    }

    @Override
    public Customer update(CustomerDTO customerDTO, Long customerId) {

        if(!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(ValidationMessage.INVALID_CUSTOMER_MESSAGE.message());
        }

        invokeValidationForContactList(customerDTO.getContactDetailsDTOS());

        Customer convertedFromDTO = customerConverter.convertDTOToDomain(customerDTO);

        return customerRepository.findById(customerId).map( customer -> {
            customer.setFirstName(convertedFromDTO.getFirstName());
            customer.setLastName(convertedFromDTO.getLastName());
            customer.setGender(convertedFromDTO.getGender());
            customer.setDateOfBirth(convertedFromDTO.getDateOfBirth());
            customer.setContactDetails(convertedFromDTO.getContactDetails());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new IllegalArgumentException(ValidationMessage.INVALID_CUSTOMER_MESSAGE.message()));
    }

    private void invokeValidationForContactList(List<ContactDetailsDTO> contactDetailsDTOList){
        contactDetailsDTOList.forEach(contactDetailsDTO -> {
            contactDetailsValidator.validate(contactDetailsDTO).failOnErrors();
        });
    }
}
