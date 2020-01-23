package com.itis.javalab.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.javalab.context.annotations.Autowired;
import com.itis.javalab.context.annotations.Controller;
import com.itis.javalab.controllers.interfaces.Handler;
import com.itis.javalab.controllers.interfaces.Mapping;
import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.services.interfaces.LoginService;
import com.itis.javalab.services.interfaces.ParametrLoader;
import lombok.NoArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@Mapping("/login")
@NoArgsConstructor
public class LoginController implements Handler {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ParametrLoader parametrLoader;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getMethod());
        if(request.getMethod().equals("GET")){
            doGet(request,response);
        }
        else if(request.getMethod().equals("POST")){
            doPost(request,response);
        }
    }

    @Override
    public void setServiceConfig() {

    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String, Object> params = parametrLoader.getLoginParams(request);
            ServiceDto result = loginService.doLoginProcess(params);
            response.getWriter().write(objectMapper.writeValueAsString(result.getResultParams()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getServletContext().getRequestDispatcher("/WEB-INF/templates/login.ftl").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
