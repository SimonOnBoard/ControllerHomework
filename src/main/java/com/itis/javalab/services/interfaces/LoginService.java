package com.itis.javalab.services.interfaces;

import com.itis.javalab.context.annotations.Component;
import com.itis.javalab.dto.system.ServiceDto;

import java.util.Map;

@Component
public interface LoginService {
    ServiceDto doLoginProcess(Map<String, Object> data);
}
