package com.cams.validation.model;

import com.cams.validation.exception.ValidationException;

import java.util.List;

public class ValidationResultWrapper {

    private static final String VALIDATION_ERROR = "Validation Failed";

    private List<ValidationResult> validationResultList;

    public ValidationResultWrapper(List<ValidationResult> validationResultList) {
        this.validationResultList = validationResultList;
    }

    public void failOnErrors(){
        if(!this.validationResultList.isEmpty()){
            throw new ValidationException(VALIDATION_ERROR, validationResultList);
        }
    }

}
