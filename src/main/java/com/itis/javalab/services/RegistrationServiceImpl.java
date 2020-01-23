package com.itis.javalab.services;

import com.itis.javalab.context.annotations.Autowired;
import com.itis.javalab.dao.interfaces.AuthDTODao;
import com.itis.javalab.dao.interfaces.UserDao;
import com.itis.javalab.dto.entity.AuthDataDTO;
import com.itis.javalab.dto.system.ServiceDto;
import com.itis.javalab.models.User;
import com.itis.javalab.services.interfaces.PhotoSaver;
import com.itis.javalab.services.interfaces.RegistrationService;
import com.itis.javalab.services.interfaces.RegistrationValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private PhotoSaver photoSaver;
    @Autowired
    private RegistrationValidator validator;
    @Autowired
    private AuthDTODao authDtoDao;
    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private String realPath;

    public RegistrationServiceImpl() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ServiceDto doRegistrationProcess(Map<String, Object> data) {
        ServiceDto resultDto = null;
        List<String> errors = validator.validate(data);
        boolean checkErrors = this.checkErrorsSize(errors);
        if (!checkErrors) {
            Optional<AuthDataDTO> authData = authDtoDao.findByName((String) data.get("name"));
            if (authData.isPresent()) {
                ArrayList<String> errors1 = new ArrayList<>();
                errors1.add("Пользователь с таким логином уже существует");
                resultDto = prepareDto(errors1, 0);
            } else {
                String photoPath = photoSaver.save(data.get("photo"),realPath);
                userDao.save(getUserObject(data, photoPath));
                resultDto = prepareDto(errors, 1);
            }
        } else {
            resultDto = prepareDto(errors, 0);
        }
        return resultDto;
    }

    private User getUserObject(Map<String, Object> data, String photoPath) {
        Date birth = Date.valueOf((String) data.get("birth"));
        String nick = (String) data.get("nick");
        String login = (String) data.get("name");
        String password = (String) data.get("password");
        String mail = (String) data.get("email");
        AuthDataDTO authDataDTO = new AuthDataDTO(null, login, bCryptPasswordEncoder.encode(password), null);
        return new User(nick, mail, birth, LocalDateTime.now(), photoPath, "user", authDataDTO);
    }

    @Override
    public void setConfig(String realPath) {
        this.realPath = realPath;
    }

    private ServiceDto prepareDto(List<String> errors, int status) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("errors", errors);
        return ServiceDto.builder().service(1).resultParams(params).chatId(null).build();
    }

    private boolean checkErrorsSize(List<String> errors) {
        return errors.size() != 0;
    }

}
