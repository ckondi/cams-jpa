package com.cams.validation.model;

public class ValidationResult {

    private final String field;
    private final String invalidValue;

    private String validationMessage;

    public ValidationResult(String field, String invalidValue) {
        this.field = field;
        this.invalidValue = invalidValue;
    }

    public ValidationResult(String field, String invalidValue, String validationMessage) {
        this.field = field;
        this.invalidValue = invalidValue;
        this.validationMessage = validationMessage;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }


}
