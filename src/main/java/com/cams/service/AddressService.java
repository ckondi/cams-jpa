package com.cams.service;

import com.cams.domain.models.Address;
import com.cams.dto.AddressDTO;

import java.util.List;

public interface AddressService {

    List<Address> findAllForCustomer(Long customerId);

    Address findByCustomerIdAddressId(Long customerId, Long addressId);

    void create(List<AddressDTO> addressDTOList, Long customerId);

    Address update(AddressDTO addressDTO, Long customerId, Long addressId);
}
