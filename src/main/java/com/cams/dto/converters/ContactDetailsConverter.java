package com.cams.dto.converters;

import com.cams.domain.models.ChannelType;
import com.cams.domain.models.ContactDetails;
import com.cams.dto.ContactDetailsDTO;
import org.springframework.stereotype.Component;

import static com.cams.util.UtilConstants.Y_LITERAL;

@Component
public class ContactDetailsConverter implements Converters<ContactDetails, ContactDetailsDTO>{

    @Override
    public ContactDetails convertDTOToDomain(ContactDetailsDTO dtoObject) {
        ContactDetails contactDetails = new ContactDetails(dtoObject.getValue(),
                ChannelType.getFromValue(dtoObject.getChannelType().toUpperCase()),
                dtoObject.getPrimary().equalsIgnoreCase(Y_LITERAL),
                dtoObject.getVerified().equalsIgnoreCase(Y_LITERAL), dtoObject.getTag());
        return contactDetails;
    }

}
