package com.cams.validation.model;

public enum ValidationMessage {

    DATE_VALIDATION_MESSAGE("Invalid Value Passed"),
    INVALID_CUSTOMER_MESSAGE("Customer Not in System"),
    INVALID_ADDRESS_ID("Invalid AddressId passed"),

    BIZ_MAX_ADDRESS_REACHED("Max Address Entried Reached"),
    BIZ_NO_ACTIVE_ADDRESS_PASSED("No Active Address Passed"),
    BIZ_ACTIVE_TO_INACTIVE_ADDRESS_UPDATE("Trying to inactivate the Active Address "),
    BIZ_ONLY_ONE_ACTIVE_ADDRESS_ALLOWED("Only one active address allowed"),
    BIZ_ADDRESS_UPDATED_RESTRICTED("Address update restricted due to policy");
    ;

    private String message;

    ValidationMessage(String s) {
        this.message = s;
    }

    public String message(){
        return this.message;
    }
}
