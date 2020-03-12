package com.cams.service.impl;

import com.cams.domain.models.ChannelType;
import com.cams.domain.models.ContactDetails;
import com.cams.domain.models.Customer;
import com.cams.dto.converters.ContactDetailsConverter;
import com.cams.dto.converters.CustomerConverter;
import com.cams.repository.CustomerRepository;
import com.cams.validation.ContactDetailsValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private ContactDetailsValidator contactDetailsValidator;
    @Mock
    private CustomerConverter customerConverter;
    @Mock
    private ContactDetailsConverter contactDetailsConverter;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    public void findAll() {
        Long[] customerIds = {123L, 256L, 765L};
        when(customerRepository.findAll()).thenReturn(createCustomerWithContactDetails(customerIds));
        List<Customer> customerList = customerServiceImpl.findAll();
        Assert.assertEquals(customerList.size(), 3);
        Assert.assertArrayEquals(customerList.stream().map(x -> x.getId()).toArray(), customerIds);
    }

    @Test
    public void findByLastName() {
        Long suffixForLastName = 525L;
        when(customerRepository.findByLastName("LN" + String.valueOf(suffixForLastName))).thenReturn(createCustomerWithContactDetails(suffixForLastName));
        List<Customer> customerList = customerServiceImpl.findByLastName("LN" + String.valueOf(suffixForLastName));
        Assert.assertEquals(customerList.size(), 1);
        Assert.assertEquals(customerList.stream().filter(x -> x.getId().compareTo(suffixForLastName) == 0).findFirst().get().getId(), suffixForLastName);
    }

    @Test
    public void create() {
        List<Integer> list = Arrays.asList(5,6,8,8,9,12,15);
        Set<Integer> intSet = new HashSet<>(list);
        /*intSet.addAll(list);*/
        System.out.println(list.size());
        System.out.println(intSet.size());

    }

    @Test
    public void update() {
    }

    private List<Customer> createCustomerWithContactDetails(Long... ids) {
        List<Customer> customerList = new ArrayList<>();
        for(Long id : ids){
            ContactDetails contactDetails = new ContactDetails("+4467463" + id, ChannelType.MOBILE, true, false, "cell");
            ContactDetails contactDetails1 = new ContactDetails("wanderer.wiz" + id, ChannelType.SOCIAL, true, false, "insta");

            Customer customer = new Customer("FN" +id, "LN" +id, "M", LocalDate.of(1965,05,05), Arrays.asList(contactDetails, contactDetails1));
            customer.setId(id);
            customer.setCreatedDateTime(Date.from(Instant.now()));
            customer.setUpdatedDateTime(Date.from(Instant.now()));
            customerList.add(customer);
        }
        return customerList;

    }

}