package com.cams.dto.converters;

import com.cams.domain.models.ContactDetails;
import com.cams.domain.models.Customer;
import com.cams.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter implements Converters<Customer, CustomerDTO> {

    private ContactDetailsConverter contactDetailsConverter;

    public CustomerConverter(ContactDetailsConverter contactDetailsConverter) {
        this.contactDetailsConverter = contactDetailsConverter;
    }

    @Override
    public Customer convertDTOToDomain(CustomerDTO customerDTO) {
        List<ContactDetails> contactDetailsList = new ArrayList<>();
        customerDTO.getContactDetailsDTOS().forEach(contactDetailsDTO -> {
                    contactDetailsList.add(contactDetailsConverter.convertDTOToDomain(contactDetailsDTO));
        });

        Customer customer = new Customer(customerDTO.getFirstName(),customerDTO.getLastName(),customerDTO.getGender(),
                LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(customerDTO.getDateOfBirth())),contactDetailsList);

        return customer;
    }

}
