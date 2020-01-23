package com.itis.javalab.controllers.interfaces;

import com.itis.javalab.context.annotations.Component;
import com.itis.javalab.context.annotations.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public interface Handler {
    void handle(HttpServletRequest request, HttpServletResponse response);
    void setServiceConfig();
}
