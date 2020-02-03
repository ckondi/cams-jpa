package com.cams.service.impl;

import com.cams.domain.models.Address;
import com.cams.domain.models.Customer;
import com.cams.dto.AddressDTO;
import com.cams.dto.converters.AddressConverter;
import com.cams.repository.AddressRepository;
import com.cams.repository.CustomerRepository;
import com.cams.service.AddressService;
import com.cams.validation.AddressValidator;
import com.cams.validation.model.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private final AddressValidator addressValidator;
    @Autowired
    private final AddressConverter addressConverter;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public AddressServiceImpl(AddressValidator addressValidator, AddressConverter addressConverter, AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressValidator = addressValidator;
        this.addressConverter = addressConverter;
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Address> findAllForCustomer(Long customerId) {
        if(!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(ValidationMessage.INVALID_CUSTOMER_MESSAGE.message());
        }

        return addressRepository.findAddressesForCustomer(customerId);
    }

    @Override
    public Address findByCustomerIdAddressId(Long customerId, Long addressId) {
        if(!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(ValidationMessage.INVALID_CUSTOMER_MESSAGE.message());
        }

        if(!addressRepository.existsById(addressId)) {
            throw new IllegalArgumentException(ValidationMessage.INVALID_ADDRESS_ID.message());
        }
        Optional<Address > optionalAddress = addressRepository.findByCustomerIdAddressId(customerId, addressId);
        return optionalAddress.isPresent() ? optionalAddress.get() : null;
    }

    @Override
    public void create(List<AddressDTO> addressDTOList, Long customerId) {

        if(!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(ValidationMessage.INVALID_CUSTOMER_MESSAGE.message());
        }

        addressDTOList.forEach((addressDTO)-> addressValidator.validate(Arrays.asList(addressDTO)).failOnErrors());

        Customer customer = customerRepository.findById(customerId).get();
        List<Address> addressListFromDB = addressRepository.findAddressesForCustomer(customerId);

        addressValidator.validateCombination(null, customer).failOnErrors();
        addressValidator.validateWithBizRulesForCreate(addressDTOList, addressListFromDB).failOnErrors();

        List<Address> addressList = new ArrayList<>();
        addressDTOList.forEach((addressDTO -> addressList.add(addressConverter.convertDTOToDomain(addressDTO))));
        addressList.forEach(address -> {
            address.setCustomer(customer);
        });
        addressRepository.saveAll(addressList);
    }

    @Override
    public Address update(AddressDTO addressDTO, Long customerId, Long addressId) {
        if(!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(ValidationMessage.INVALID_CUSTOMER_MESSAGE.message());
        }

        if(!addressRepository.existsById(addressId)) {
            throw new IllegalArgumentException(ValidationMessage.INVALID_ADDRESS_ID.message());
        }
//        Todo call validation
        addressValidator.validate(Arrays.asList(addressDTO)).failOnErrors();

        Address savedAddress = addressRepository.findById(addressId).get();
        Customer customer = customerRepository.findById(customerId).get();

        addressValidator.validateCombination(savedAddress, customer).failOnErrors();
        addressValidator.validateWithBizRulesForUpdate(Arrays.asList(addressDTO), Arrays.asList(savedAddress)).failOnErrors();

        Address toUpdate = addressConverter.convertDTOToDomain(addressDTO);
        savedAddress.setCustomer(customer);
        savedAddress.setAddressLine1(toUpdate.getAddressLine1());
        savedAddress.setAddressLine2(toUpdate.getAddressLine2());
        savedAddress.setCity(toUpdate.getCity());
        savedAddress.setPostcode(toUpdate.getPostcode());
        savedAddress.setCountry(toUpdate.getCountry());

        savedAddress.setActive(toUpdate.isActive());

        return addressRepository.save(savedAddress);
    }
}
