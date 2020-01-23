package com.itis.javalab.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.javalab.context.interfaces.ApplicationContext;
import com.itis.javalab.dao.interfaces.ProductDao;
import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.models.Product;
import com.itis.javalab.services.interfaces.ParametrLoader;
import com.itis.javalab.services.interfaces.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//@MultipartConfig
//@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao;
    private ParametrLoader parametrLoader;
    private ProductService productService;
    private ObjectMapper objectMapper;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDao.findProductsOnPage(1000000L, 0L);
        req.setAttribute("products", products);
        req.getServletContext().getRequestDispatcher("/WEB-INF/templates/products.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> data = parametrLoader.getProductParameters(req);
        ServiceDto result = productService.saveProduct(data);
        String toPrint = objectMapper.writeValueAsString(result.getParametr("product"));
        resp.getWriter().write(toPrint);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext) servletContext.getAttribute("context");
        productDao = context.getComponent("ProductDao");
        parametrLoader = context.getComponent("ParametrLoader");
        productService = context.getComponent("ProductService");
        productService.setConfig(servletContext.getRealPath(""));
        objectMapper = new ObjectMapper();
    }
}
