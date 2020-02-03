package com.cams.resource;

import com.cams.domain.models.Address;
import com.cams.dto.AddressDTO;
import com.cams.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressResource {

    @Autowired
    private final AddressService addressService;

    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/customer/{customerId}/addresses" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Address> findAllForCustomer(@PathVariable Long customerId){ return addressService.findAllForCustomer(customerId);}

    @GetMapping(value = "/customer/{customerId}/address/{addressId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    Address fetchByLastName(@PathVariable Long customerId, @PathVariable Long addressId) {
        return addressService.findByCustomerIdAddressId(customerId, addressId);
    }

    @PostMapping(value = "/customer/{customerId}/address" , consumes = MediaType.APPLICATION_JSON_VALUE)
    void create(@RequestBody List<AddressDTO> addressDTOList, @PathVariable Long customerId ){
        addressService.create(addressDTOList, customerId);
    }

    @PutMapping(value = "/customer/{customerId}/address/{addressId}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    Address update(@RequestBody AddressDTO addressDTO, @PathVariable Long customerId, @PathVariable Long addressId){
        return addressService.update(addressDTO, customerId, addressId);
    }

}
