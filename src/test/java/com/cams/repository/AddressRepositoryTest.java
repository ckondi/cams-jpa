package com.cams.repository;

import com.cams.domain.models.Address;
import com.cams.domain.models.ChannelType;
import com.cams.domain.models.ContactDetails;
import com.cams.domain.models.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.yml")
class AddressRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository underTest;

    private static final long CUSTOMER_ID = 123L;

    private static final long ADDRESS_ID = 202L;

    private Customer customerCreatedForTest;

    private List<Address> addressListCreatedForTest;

    @Test
    void findAddressesForCustomer() {
        Assert.assertEquals(underTest.findAddressesForCustomer(CUSTOMER_ID).size(), 3);
    }

    @Test
    void findByCustomerIdAddressId() {

        Assert.assertEquals(underTest.findByCustomerIdAddressId(CUSTOMER_ID, ADDRESS_ID).isPresent(), true);

        Assert.assertThat(underTest.findByCustomerIdAddressId(CUSTOMER_ID, ADDRESS_ID).get().getId(), is(ADDRESS_ID));
    }

    /*private Customer createCustomerWithContactDetailsForID() {
        ContactDetails contactDetails = new ContactDetails("+44674638373", ChannelType.MOBILE, true, false, "cell");
        ContactDetails contactDetails1 = new ContactDetails("wanderer.wiz", ChannelType.SOCIAL, true, false, "insta");

        Customer customer = new Customer("FN1", "LN1", "M", LocalDate.of(1965,05,05), Arrays.asList(contactDetails, contactDetails1));
        customer.setCreatedDateTime(Date.from(Instant.now()));
        customer.setUpdatedDateTime(Date.from(Instant.now()));

        return customerRepository.save(customer);
    }

    private List<Address> createAddressForCustomer(Customer customer) {

        Address address1 = new Address("A1Line1", "A2Line2", "city1", "POSCOD1", "GB");
        address1.setCustomer(customer);

        Address address2 = new Address("A2Line1", "A2Line2", "city2", "POSCOD2", "US");
        address2.setCustomer(customer);

        Address address3 = new Address("A3Line1", "A3Line2", "city3", "POSCOD3", "IN");
        address3.setCustomer(customer);

        return underTest.saveAll(Arrays.asList(address1,address2, address3));

    }*/

}