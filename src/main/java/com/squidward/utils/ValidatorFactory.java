package com.squidward.utils;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidatorFactory {

    private javax.validation.ValidatorFactory validatorFactory;

    public ValidatorFactory() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    public Validator getValidator() {
        return validatorFactory.getValidator();
    }
}
