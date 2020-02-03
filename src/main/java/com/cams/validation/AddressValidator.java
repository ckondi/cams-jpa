package com.cams.validation;

import com.cams.domain.models.Address;
import com.cams.domain.models.Customer;
import com.cams.dto.AddressDTO;
import com.cams.validation.model.ValidationMessage;
import com.cams.validation.model.ValidationResult;
import com.cams.validation.model.ValidationResultWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.cams.util.UtilConstants.IDENTIFIER;
import static com.cams.util.UtilConstants.Y_LITERAL;

@Component
public class AddressValidator implements ValidationStrategy<List<AddressDTO>, List<Address>>{

    @Value( "${cams.address.update.frequency.in.days:7}" )
    private int addressUpdateFrequency;

    @Value( "${cams.address.max.rec.limit:5}" )
    private int addressMaxRecordLimit;

    @Override
    public ValidationResultWrapper validate(List<AddressDTO> request) {

        List<ValidationResult> validationResults = new ArrayList<>();
        ValidationResultWrapper validationResultWrapper = new ValidationResultWrapper(validationResults);
        return validationResultWrapper;
    }

    @Override
    public ValidationResultWrapper validateWithBizRulesForCreate(List<AddressDTO> addressDTOList, List<Address> addressList) {
        List<ValidationResult> validationResults = new ArrayList<>();

        int addressesCountInDb = addressList.size();
        int addressesCountInRequest = addressDTOList.size();

        boolean activeAddressInDb = addressList.stream().anyMatch(address -> address.isActive());
        long activeAddressesCountInRequest = addressDTOList.stream()
                .filter(addressDTO -> addressDTO.getActive().equalsIgnoreCase(Y_LITERAL))
                .count();
//        Max Address Limit Check
        if(addressesCountInRequest > addressMaxRecordLimit || (addressesCountInRequest + addressesCountInDb > addressMaxRecordLimit) ){
            validationResults.add(new ValidationResult(Address.class.toString(),addressDTOList.toString(), ValidationMessage.BIZ_MAX_ADDRESS_REACHED.message()));
        }
        // Validations for only one Active address
        else if ((activeAddressInDb && activeAddressesCountInRequest > 0) || (!activeAddressInDb && activeAddressesCountInRequest > 1)) {
            validationResults.add(new ValidationResult(Address.class.toString(),addressDTOList.toString(), ValidationMessage.BIZ_ONLY_ONE_ACTIVE_ADDRESS_ALLOWED.message()));
        }
        else if (!activeAddressInDb && activeAddressesCountInRequest == 0){
            validationResults.add(new ValidationResult(Address.class.toString(),addressDTOList.toString(), ValidationMessage.BIZ_NO_ACTIVE_ADDRESS_PASSED.message()));
        }

        return new ValidationResultWrapper(validationResults);
    }

    @Override
    public ValidationResultWrapper validateWithBizRulesForUpdate(List<AddressDTO> addressDTOList, List<Address> addressList) {
        List<ValidationResult> validationResults = new ArrayList<>();
        Address address = addressList.get(0);

        Date updatedDateTime = address.getUpdatedDateTime();
        Date secondDate = Date.from(Instant.now());

        long diffInMillies = Math.abs(secondDate.getTime() - updatedDateTime.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);


        if(diffInDays < addressUpdateFrequency){
            validationResults.add(new ValidationResult("Address", address.getUpdatedDateTime().toString(), ValidationMessage.BIZ_ADDRESS_UPDATED_RESTRICTED.message() ));
        }
        addressDTOList.forEach(addressDTO -> {
            if(address.isActive() && !addressDTO.getActive().equalsIgnoreCase(Y_LITERAL)){
                validationResults.add(new ValidationResult(Customer.class.toString(),addressDTOList.toString(), ValidationMessage.BIZ_ACTIVE_TO_INACTIVE_ADDRESS_UPDATE.message()));
            }
        });

        return new ValidationResultWrapper(validationResults);
    }


    public ValidationResultWrapper validateCombination(Address address, Customer customer){

        List<ValidationResult> validationResults = new ArrayList<>();

        if(null == customer){
            ValidationResult validationResult = new ValidationResult(IDENTIFIER,String.valueOf(customer.getId()),ValidationMessage.INVALID_CUSTOMER_MESSAGE.message());
            validationResults.add(validationResult);
        }
        else if(null != address && null != customer &&
                (address.getCustomer().getId() != customer.getId())
        ){
            ValidationResult validationResult = new ValidationResult(IDENTIFIER,customer.getId() + ":" +address.getId(),ValidationMessage.INVALID_CUSTOMER_MESSAGE.message());
            validationResults.add(validationResult);
        }

        return new ValidationResultWrapper(validationResults);
    }
}
