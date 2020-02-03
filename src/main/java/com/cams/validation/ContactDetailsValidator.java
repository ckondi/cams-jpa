package com.cams.validation;

import com.cams.domain.models.ChannelType;
import com.cams.domain.models.ContactDetails;
import com.cams.dto.ContactDetailsDTO;
import com.cams.validation.model.ValidationMessage;
import com.cams.validation.model.ValidationResult;
import com.cams.validation.model.ValidationResultWrapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.cams.util.UtilConstants.CHANNEL_TYPE;

@Component
public class ContactDetailsValidator implements ValidationStrategy<ContactDetailsDTO, ContactDetails> {

    @Override
    public ValidationResultWrapper validate(ContactDetailsDTO request) {
        List<ValidationResult> validationResults = new ArrayList<>();

        try{
            ChannelType channelType = ChannelType.getFromValue(request.getChannelType().toUpperCase());
        }
        catch (IllegalArgumentException | NullPointerException e){
            ValidationResult validationResult = new ValidationResult(CHANNEL_TYPE,request.getChannelType(), ValidationMessage.INVALID_CUSTOMER_MESSAGE.message() + ", valid values: " + ChannelType.asString());
            validationResults.add(validationResult);
        }

        return new ValidationResultWrapper(validationResults);
    }
}
