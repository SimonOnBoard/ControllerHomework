package com.itis.javalab.models;

import com.itis.javalab.dto.entity.AuthDataDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String nick;
    private String email;
    private Date birthDate;
    private LocalDateTime regiStrationDate;
    private String photoPath;
    private String role;
    private AuthDataDTO authDataDTO;

    public User(String nick, String email, Date birth_date, LocalDateTime regiStrationDate, String photoPath, String role, AuthDataDTO authDataDTO) {
        this.nick = nick;
        this.email = email;
        this.birthDate = birth_date;
        this.regiStrationDate = regiStrationDate;
        this.photoPath = photoPath;
        this.role = role;
        this.authDataDTO = authDataDTO;
    }
}
