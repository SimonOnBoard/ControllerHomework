package com.itis.javalab.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.javalab.Parametrs;
import com.itis.javalab.context.annotations.Autowired;
import com.itis.javalab.context.annotations.Controller;
import com.itis.javalab.controllers.interfaces.Handler;
import com.itis.javalab.controllers.interfaces.Mapping;
import com.itis.javalab.dao.interfaces.ProductDao;
import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.models.Product;
import com.itis.javalab.services.interfaces.ParametrLoader;
import com.itis.javalab.services.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@Mapping("/products")
public class ProductController implements Handler {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ParametrLoader parametrLoader;
    @Autowired
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    boolean firstPostRequest = false;

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
        productService.setConfig(Parametrs.getPath());
    }
    private void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println(request.getParts().size());
            request.setCharacterEncoding("UTF-8");
            Map<String, Object> data = parametrLoader.getProductParameters(request);
            ServiceDto result = productService.saveProduct(data);
            String toPrint = objectMapper.writeValueAsString(result.getParametr("product"));
            response.getWriter().write(toPrint);
        } catch (IOException | ServletException e) {
            throw new IllegalStateException(e);
        }
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productDao.findProductsOnPage(1000000L, 0L);
        request.setAttribute("products", products);
        try {
            request.getServletContext().getRequestDispatcher("/WEB-INF/templates/products.ftl").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
