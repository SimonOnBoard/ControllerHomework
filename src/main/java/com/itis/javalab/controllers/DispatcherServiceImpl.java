package com.itis.javalab.controllers;

import com.itis.javalab.context.annotations.Autowired;
import com.itis.javalab.context.interfaces.ApplicationContext;
import com.itis.javalab.controllers.interfaces.Handler;
import com.itis.javalab.controllers.interfaces.DispatcherService;
import com.itis.javalab.controllers.interfaces.Mapping;
import org.reflections.Reflections;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DispatcherServiceImpl implements DispatcherService {
    @Autowired
    private Reflections reflections;
    private Map<String,String> exsControllers;
    private Map<String, Handler> loadedControllers;
    @Autowired
    private ApplicationContext applicationContext;

    public DispatcherServiceImpl() {
        exsControllers = new HashMap<>();
        loadedControllers = new HashMap<>();
    }

    public void loadControllers() {
        Class controllerServiceClass = Handler.class;
        //здесь по хорошему должна быть проверка на то что данные контроллеры помечены анотацией конроллер
        Set<Class<?>> components = reflections.getSubTypesOf(controllerServiceClass);
        for(Class current: components){
            String[] names = current.getName().split("\\.");
            Mapping mapping = (Mapping) current.getAnnotation(Mapping.class);
            if(mapping == null){
                throw new IllegalStateException("Контроллер не имеет нужной анотации");
            }
            exsControllers.put(mapping.value(),names[names.length - 1]);
        }
    }

    @Override
    public Handler getCurrentController(HttpServletRequest request) {
        String path = "/" + getCurrentPath(request);
        Handler controller = loadedControllers.get(path);
        if(controller == null){
           String name = exsControllers.get(path);
           if(name != null) {
               controller = applicationContext.getComponent(name);
               controller.setServiceConfig();
               loadedControllers.put(path, controller);
           }
           else {
               return null;
           }
        }
        return controller;
    }

    private String getCurrentPath(HttpServletRequest request) {

        String[] strings = request.getRequestURL().toString().split("\\/");
        if(strings.length < 4) return strings[0];
        return strings[3];
    }
}
