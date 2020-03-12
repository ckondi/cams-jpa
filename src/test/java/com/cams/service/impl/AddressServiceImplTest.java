package com.cams.service.impl;

import com.cams.domain.models.Address;
import com.cams.domain.models.ChannelType;
import com.cams.domain.models.ContactDetails;
import com.cams.domain.models.Customer;
import com.cams.dto.converters.AddressConverter;
import com.cams.repository.AddressRepository;
import com.cams.repository.CustomerRepository;
import com.cams.validation.AddressValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AddressServiceImplTest {

    @Autowired
    private CustomerServiceImpl underTest;

    @Mock
    private AddressValidator addressValidator;
    @Mock
    private AddressConverter addressConverter;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void findAllForCustomer() {
    }

    @Test
    public void findByCustomerIdAddressId() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    private Customer createCustomerWithContactDetails(Long id) {
        ContactDetails contactDetails = new ContactDetails("+44674638373", ChannelType.MOBILE, true, false, "cell");
        ContactDetails contactDetails1 = new ContactDetails("wanderer.wiz", ChannelType.SOCIAL, true, false, "insta");

        Customer customer = new Customer("FN1", "LN1", "M", LocalDate.of(1965,05,05), Arrays.asList(contactDetails, contactDetails1));
        customer.setId(id);
        customer.setCreatedDateTime(Date.from(Instant.now()));
        customer.setUpdatedDateTime(Date.from(Instant.now()));
        return customer;

    }

    private List<Address> createAddressForCustomer(Customer customer) {

        Address address1 = new Address("A1Line1", "A2Line2", "city1", "POSCOD1", "GB");
        address1.setCustomer(customer);

        Address address2 = new Address("A2Line1", "A2Line2", "city2", "POSCOD2", "US");
        address2.setCustomer(customer);

        Address address3 = new Address("A3Line1", "A3Line2", "city3", "POSCOD3", "IN");
        address3.setCustomer(customer);

        return Arrays.asList(address1,address2, address3);

    }
}