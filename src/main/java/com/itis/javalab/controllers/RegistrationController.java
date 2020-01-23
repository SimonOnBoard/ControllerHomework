package com.itis.javalab.controllers;


import com.itis.javalab.Parametrs;
import com.itis.javalab.context.annotations.Autowired;
import com.itis.javalab.context.annotations.Controller;
import com.itis.javalab.controllers.interfaces.Handler;
import com.itis.javalab.controllers.interfaces.Mapping;
import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.services.interfaces.ParametrLoader;
import com.itis.javalab.services.interfaces.RegistrationService;
import com.itis.javalab.services.interfaces.ResultWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@Mapping("/registration")
public class RegistrationController implements Handler {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ParametrLoader parametrLoader;
    @Autowired
    private ResultWriter resultWriter;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getMethod());
        if (request.getMethod().equals("GET")) {
            doGet(request, response);
        } else if (request.getMethod().equals("POST")) {
            doPost(request, response);
        }
    }

    @Override
    public void setServiceConfig() {
        registrationService.setConfig(Parametrs.getPath());
    }


    private void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String, Object> params = parametrLoader.getRegistrationParams(request);
            ServiceDto result = registrationService.doRegistrationProcess(params);
            int var = resultWriter.prepareRegistrationResult(request, result);
            switch (var) {
                case 1:
                    response.sendRedirect("/login");
                    break;
                case 0:
                    request.getServletContext().getRequestDispatcher("/WEB-INF/templates/registration.ftl").forward(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getServletContext().getRequestDispatcher("/WEB-INF/templates/registration.ftl").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
