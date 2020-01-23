package com.itis.javalab.services;

import com.itis.javalab.context.annotations.Autowired;
import com.itis.javalab.dao.interfaces.ProductDao;
import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.models.Product;
import com.itis.javalab.services.interfaces.PhotoSaver;
import com.itis.javalab.services.interfaces.ProductService;

import java.util.HashMap;
import java.util.Map;

public class ProductServiceImpl implements ProductService{
        @Autowired
        private ProductDao productDao;
        @Autowired
        private PhotoSaver photoSaver;

        private String realPath;

        @Override
        public ServiceDto saveProduct(Map<String, Object> data) {
            Product product = new Product();
            String name = (String) data.get("name");
            Double cost = Double.parseDouble((String) data.get("cost"));
            Integer count = Integer.parseInt((String) data.get("count"));
            String photoPath = photoSaver.saveProductPhoto(realPath, data.get("photo"));
            product.setName(name);
            product.setCount(count);
            product.setEnded(Boolean.FALSE);
            product.setPhotoPath(photoPath);
            product.setPrice(cost);
            productDao.save(product);
            return getServiceDto(product, 1);
        }

    @Override
    public void setConfig(String realPath) {
        this.realPath = realPath;
    }

    private ServiceDto getServiceDto(Product product, int status) {
            Map<String, Object> params = new HashMap<>();
            params.put("status", status);
            params.put("product", product);
            return ServiceDto.builder().service(1).resultParams(params).chatId(null).build();
        }
}
