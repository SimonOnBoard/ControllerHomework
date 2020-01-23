package com.itis.javalab.services.interfaces;

import com.itis.javalab.context.annotations.Component;

import java.util.List;
import java.util.Map;

@Component
public interface RegistrationValidator {
    List<String> validate(Map<String, Object> data);
}
