package com.cams.dto.converters;

import com.cams.domain.models.Address;
import com.cams.dto.AddressDTO;
import org.springframework.stereotype.Component;

import static com.cams.util.UtilConstants.Y_LITERAL;

@Component
public class AddressConverter implements Converters<Address, AddressDTO>{

    @Override
    public Address convertDTOToDomain(AddressDTO dtoObject) {
        Address address = new Address(dtoObject.getAddressLine1(), dtoObject.getAddressLine2(),
                dtoObject.getCity(),dtoObject.getPostcode(),dtoObject.getCountry());
        address.setActive(null != dtoObject.getActive() &&  dtoObject.getActive().equalsIgnoreCase(Y_LITERAL));
        return address;
    }
}
