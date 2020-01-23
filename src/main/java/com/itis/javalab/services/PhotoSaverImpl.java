package com.itis.javalab.services;

import com.itis.javalab.services.interfaces.PhotoSaver;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class PhotoSaverImpl implements PhotoSaver {
    @Override
    public String save(Object photo, String realPath) {
        Part p = (Part) photo;
        String photoPath = "/img/no-image.jpg";
        if (p.getSize() != 0) {
            String localdir = "avatars";
            String pathDir = realPath + File.separator + localdir;
            File dir = new File(pathDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String[] filename_data = p.getSubmittedFileName().split("\\.");
            String filename = Math.random() + "." + filename_data[filename_data.length - 1];
            String fullpath = pathDir + File.separator + filename;
            try {
                p.write(fullpath);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            photoPath = (File.separator + localdir + File.separator + filename);
        }
        return photoPath;
    }

    @Override
    public String saveProductPhoto(String realPath, Object photo) {
        Part p = (Part) photo;
        String photoPath = "/img/no-image.jpg";
        if (p.getSize() != 0) {
            String localdir = "avatars";
            String pathDir = realPath + File.separator + localdir;
            File dir = new File(pathDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String[] filename_data = p.getSubmittedFileName().split("\\.");
            String filename = Math.random() + "." + filename_data[filename_data.length - 1];
            String fullpath = pathDir + File.separator + filename;
            try {
                p.write(fullpath);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            photoPath = (File.separator + localdir + File.separator + filename);
        }
        return photoPath;
    }
}
