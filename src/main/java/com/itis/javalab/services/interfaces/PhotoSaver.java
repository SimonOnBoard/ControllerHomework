package com.itis.javalab.services.interfaces;

import com.itis.javalab.context.annotations.Component;

@Component
public interface PhotoSaver {
    String save(Object photo, String realPath);

    String saveProductPhoto(String realPath, Object photo);
}
