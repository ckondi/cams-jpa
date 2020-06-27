package com.cams.repository;

import com.cams.domain.models.ChannelType;
import com.cams.domain.models.ContactDetails;
import com.cams.domain.models.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.yml")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    private final String LAST_NAME_UNDER_TEST = "LN2";

    private static final long CUSTOMER_ID = 123L;
    private static final long CUSTOMER_ID1 = 576L;


    @Test
    void findByLastName() {
/*
        createCustomerWithContactDetails(CUSTOMER_ID);
        createCustomerWithOutContactDetails(CUSTOMER_ID1);*/

        List<Customer> customer = underTest.findByLastName(LAST_NAME_UNDER_TEST);
        boolean exists = customer.stream().anyMatch(customer1 -> customer1.getLastName().equalsIgnoreCase(LAST_NAME_UNDER_TEST));
        Assert.assertTrue(exists);
        Customer returned = customer.stream().filter(customer1 -> customer1.getId().compareTo(CUSTOMER_ID) == 0).findAny().orElse(null);
        Assert.assertNotNull(returned);
        Assert.assertThat(returned.getId(),is(CUSTOMER_ID));

    }

    @Test
    void findAll() {
/*
        createCustomerWithContactDetails(CUSTOMER_ID);
        createCustomerWithOutContactDetails(CUSTOMER_ID1);*/

        List<Customer> customerList = underTest.findAll();
        Assert.assertEquals(customerList.size(), 2);

    }

    private void createCustomerWithContactDetails(Long id) {
        ContactDetails contactDetails = new ContactDetails("+44674638373", ChannelType.MOBILE, true, false, "cell");
        ContactDetails contactDetails1 = new ContactDetails("wanderer.wiz", ChannelType.SOCIAL, true, false, "insta");

        Customer customer = new Customer("FN1", "LN1", "M", LocalDate.of(1965,05,05), Arrays.asList(contactDetails, contactDetails1));
        customer.setId(id);
        customer.setCreatedDateTime(Date.from(Instant.now()));
        customer.setUpdatedDateTime(Date.from(Instant.now()));
        underTest.save(customer);

    }

    private void createCustomerWithOutContactDetails(Long id) {
        Customer customer = new Customer("FN2", LAST_NAME_UNDER_TEST, "F", LocalDate.of(1987,11,2), null);
        customer.setId(id);
        customer.setCreatedDateTime(Date.from(Instant.now()));
        customer.setUpdatedDateTime(Date.from(Instant.now()));
        underTest.save(customer);
    }
}