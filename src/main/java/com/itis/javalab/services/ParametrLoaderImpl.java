package com.itis.javalab.services;

import com.itis.javalab.services.interfaces.ParametrLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParametrLoaderImpl implements ParametrLoader {

    @Override
    public Map<String, Object> getRegistrationParams(HttpServletRequest req) {
        Map<String,Object> params = new HashMap<>();
        params.put("birth",req.getParameter("birth"));
        params.put("nick", req.getParameter("user_name"));
        params.put("name", req.getParameter("name"));
        params.put("password", req.getParameter("password"));
        params.put("email", req.getParameter("email"));
        try {
            params.put("photo", req.getPart("photo"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ServletException e) {
            throw new IllegalStateException(e);
        }
        return params;
    }

    @Override
    public Map<String, Object> getLoginParams(HttpServletRequest req) {
        Map<String,Object> params = new HashMap<>();
        params.put("login",req.getParameter("username"));
        params.put("password", req.getParameter("password"));
        return params;
    }

    @Override
    public Map<String, Object> getProductParameters(HttpServletRequest req) {
        Map<String,Object> params = new HashMap<>();
        params.put("name",req.getParameter("name"));
        params.put("cost", req.getParameter("cost"));
        params.put("count", req.getParameter("count"));
        try {
            params.put("photo", req.getPart("photo"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ServletException e) {
            throw new IllegalStateException(e);
        }
        return params;
    }


}
