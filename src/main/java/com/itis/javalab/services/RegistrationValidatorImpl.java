package com.itis.javalab.services;

import com.itis.javalab.services.interfaces.RegistrationValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistrationValidatorImpl implements RegistrationValidator {
    @Override
    public List<String> validate(Map<String, Object> data) {
        List<String> errors = new ArrayList<>();
        this.validatePassword(data.get("password"), errors);
        return errors;
    }

    private void validatePassword(Object password, List<String> errors) {
        if (password == null) {
            errors.add("empty password");
        }
        if (((String) password).length() < 8){
            errors.add("В пароле меньше 8 символов");
        }
    }
}
