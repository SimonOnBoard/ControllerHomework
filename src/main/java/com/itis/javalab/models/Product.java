package com.itis.javalab.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itis.javalab.serializers.ProductSerializer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonSerialize(using = ProductSerializer.class)
@RequiredArgsConstructor
@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
    private Boolean ended;
    private Integer count;
    private String photoPath;

    public Product(Long id, String name, Double price, Boolean ended, Integer count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ended = ended;
        this.count = count;
    }

    public Product(Long id, String name, Double price, Boolean ended, Integer count, String photoPath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ended = ended;
        this.count = count;
        this.photoPath = photoPath;
    }
}
