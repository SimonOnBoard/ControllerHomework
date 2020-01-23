package com.itis.javalab.services.interfaces;

import com.itis.javalab.context.annotations.Component;
import com.itis.javalab.dto.system.ServiceDto;

import java.util.Map;

@Component
public interface RegistrationService {
    public ServiceDto doRegistrationProcess(Map<String, Object> data);
    public void setConfig(String realPath);
}
