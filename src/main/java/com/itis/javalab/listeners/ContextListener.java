package com.itis.javalab.listeners;

import com.itis.javalab.context.ApplicationContextReflectionBasedSecondImpl;
import com.itis.javalab.context.interfaces.ApplicationContext;
import com.itis.javalab.services.PropertiesLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String[] properties = PropertiesLoader.getProperties("/Users/nowhere/IdeaProjects/socket/src/main/resources/db.properties");
        try {
            Class.forName(properties[3]);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        ApplicationContext context1 = new ApplicationContextReflectionBasedSecondImpl(properties);
        context.setAttribute("context",context1);
    }
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
