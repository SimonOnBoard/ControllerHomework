package com.itis.javalab.services.interfaces;

import com.itis.javalab.context.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public interface ParametrLoader {
    public Map<String,Object> getRegistrationParams(HttpServletRequest req);
    public Map<String,Object> getLoginParams(HttpServletRequest req);

    Map<String, Object> getProductParameters(HttpServletRequest req);
}
