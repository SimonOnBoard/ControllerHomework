package com.itis.javalab.controllers.interfaces;

import com.itis.javalab.context.annotations.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public interface DispatcherService {
    Handler getCurrentController(HttpServletRequest request);
    void loadControllers();
}
