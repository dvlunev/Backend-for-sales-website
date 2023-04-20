package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class LoginReqDto {
    //пароль
    private String password;
    //логин
    private String username;

}
