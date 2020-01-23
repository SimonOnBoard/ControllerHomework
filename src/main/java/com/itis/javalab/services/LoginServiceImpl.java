package com.itis.javalab.services;

import com.itis.javalab.context.annotations.Autowired;
import com.itis.javalab.dao.interfaces.AuthDTODao;
import com.itis.javalab.dao.interfaces.UserDao;
import com.itis.javalab.dto.entity.AuthDataDTO;
import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.models.User;
import com.itis.javalab.services.interfaces.LoginService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthDTODao authDtoDao;
    @Autowired
    private UserDao userDao;
    private PasswordEncoder encoder;

    public LoginServiceImpl() {
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public ServiceDto doLoginProcess(Map<String, Object> data) {
        ServiceDto serviceDto = null;
        List<String> errors = new ArrayList<>();
        Optional<AuthDataDTO> authData = authDtoDao.findByName((String) data.get("login"));
        if (authData.isPresent()) {
            if (encoder.matches((CharSequence) data.get("password"), authData.get().getPassword())) {
                Optional<User> user = userDao.findByDTO(authData.get());
                if (user.isPresent()) {
                    serviceDto = prepareDto(errors, 1);
                    return serviceDto;
                } else {
                    throw new IllegalStateException("Есть параметры входа, но нет пользователя");
                }
            } else {

            }
        }
        errors.add("Wrond username or password/ User XXX not exists");
        serviceDto = prepareDto(errors, 0);
        return serviceDto;
    }

    private ServiceDto prepareDto(List<String> errors, int status) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("errors", errors);
        if (status == 1) {
            params.put("redirect", "/products");
        }
        return ServiceDto.builder().service(1).resultParams(params).chatId(null).build();
    }
}
