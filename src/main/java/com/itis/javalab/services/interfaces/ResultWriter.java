package com.itis.javalab.services.interfaces;

import com.itis.javalab.context.annotations.Component;
import com.itis.javalab.dto.system.ServiceDto;

import javax.servlet.http.HttpServletRequest;

@Component
public interface ResultWriter {
    int prepareRegistrationResult(HttpServletRequest request, ServiceDto dto);
}
