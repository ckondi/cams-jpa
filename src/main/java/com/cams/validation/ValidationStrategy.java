package com.cams.validation;

import com.cams.validation.model.ValidationResultWrapper;

import java.util.ArrayList;

public interface ValidationStrategy<T,R> {
    ValidationResultWrapper validate(T request);

    default ValidationResultWrapper validateWithBizRulesForCreate(T request, R domain) {
        return new ValidationResultWrapper(new ArrayList<>());
    }

    default ValidationResultWrapper validateWithBizRulesForUpdate(T request, R domain) {
        return new ValidationResultWrapper(new ArrayList<>());
    }
}
