package com.itis.javalab.servlets;

import com.itis.javalab.context.interfaces.ApplicationContext;
import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.services.interfaces.ParametrLoader;
import com.itis.javalab.services.interfaces.RegistrationService;
import com.itis.javalab.services.interfaces.ResultWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@MultipartConfig
//@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private RegistrationService registrationService;
    private ParametrLoader parametrLoader;
    private ResultWriter resultWriter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/templates/registration.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> params = parametrLoader.getRegistrationParams(req);
        ServiceDto result = registrationService.doRegistrationProcess(params);
        int var = resultWriter.prepareRegistrationResult(req, result);
        switch (var) {
            case 1:
                resp.sendRedirect("/login");
                break;
            case 0:
                req.getServletContext().getRequestDispatcher("/WEB-INF/templates/registration.ftl").forward(req, resp);
                break;
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext) servletContext.getAttribute("context");
        registrationService = context.getComponent("RegistrationService");
        registrationService.setConfig(servletContext.getRealPath(""));
        parametrLoader = context.getComponent("ParametrLoader");
        resultWriter = context.getComponent("ResultWriter");
    }
}
