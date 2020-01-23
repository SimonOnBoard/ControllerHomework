package com.itis.javalab.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.javalab.context.interfaces.ApplicationContext;
import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.services.interfaces.LoginService;
import com.itis.javalab.services.interfaces.ParametrLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private LoginService loginService;
    private ParametrLoader parametrLoader;
    private ObjectMapper objectMapper;
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        System.out.println(req.getRequestURL());

        String[] strings = req.getRequestURI().split(".");
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
//        String[] args = req.getRequestURI().split("/");
//
        if (url.contains("css") | url.contains("jpg")) {
            dispatcher.forward(req,resp);
        } else {
            req.getServletContext().getRequestDispatcher("/WEB-INF/templates/login.ftl").forward(req, resp);
        }
//        System.out.println(args[args.length - 1]);
//        System.out.println(strings[strings.length - 1]);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> params = parametrLoader.getLoginParams(req);
        ServiceDto result = loginService.doLoginProcess(params);
        resp.getWriter().write(objectMapper.writeValueAsString(result.getResultParams()));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext) servletContext.getAttribute("context");
        dispatcher = servletContext.getNamedDispatcher("default");
        loginService = context.getComponent("LoginService");
        parametrLoader = context.getComponent("ParametrLoader");
        objectMapper = new ObjectMapper();
    }

}
