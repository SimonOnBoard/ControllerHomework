package com.itis.javalab.services;

import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.services.interfaces.ResultWriter;

import javax.servlet.http.HttpServletRequest;

public class ResultWriterImpl implements ResultWriter {
    @Override
    public int prepareRegistrationResult(HttpServletRequest request, ServiceDto dto) {
        int status = (int) dto.getParametr("status");
        switch (status){
            case 0:
                request.setAttribute("errors",(dto.getParametr("errors")));
                break;
        }
        return status;
    }
}
