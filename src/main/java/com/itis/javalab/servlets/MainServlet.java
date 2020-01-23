package com.itis.javalab.servlets;

import com.itis.javalab.Parametrs;
import com.itis.javalab.context.interfaces.ApplicationContext;
import com.itis.javalab.controllers.DispatcherServiceImpl;
import com.itis.javalab.controllers.interfaces.Handler;
import com.itis.javalab.controllers.interfaces.DispatcherService;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet("/")
public class MainServlet extends HttpServlet {
    private RequestDispatcher dispatcher;
    private DispatcherService dispatcherService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Handler controller = dispatcherService.getCurrentController(req);
        if (controller == null) {
            System.out.println(req.getRequestURL());
            dispatcher.forward(req,resp);
        } else {
            controller.handle(req, resp);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext) servletContext.getAttribute("context");
        dispatcher = servletContext.getNamedDispatcher("default");
        dispatcherService = context.getComponent("DispatcherService");
        dispatcherService.loadControllers();
        Parametrs.setPath(servletContext.getRealPath(""));
    }
}
