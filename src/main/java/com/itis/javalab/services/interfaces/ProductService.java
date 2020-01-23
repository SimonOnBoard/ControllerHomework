package com.itis.javalab.services.interfaces;

import com.itis.javalab.context.annotations.Component;
import com.itis.javalab.dto.system.ServiceDto;

import java.util.Map;

@Component
public interface ProductService {
    ServiceDto saveProduct(Map<String, Object> data);
    void setConfig(String realPath);
}
