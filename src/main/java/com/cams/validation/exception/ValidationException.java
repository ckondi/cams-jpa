package com.cams.validation.exception;

import com.cams.validation.model.ValidationResult;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<ValidationResult> validationResults;

    public ValidationException(String message, List<ValidationResult> validationResults) {
        super(message);
        this.validationResults = validationResults;
    }

    public List<ValidationResult> getValidationResults() {
        return validationResults;
    }
}
