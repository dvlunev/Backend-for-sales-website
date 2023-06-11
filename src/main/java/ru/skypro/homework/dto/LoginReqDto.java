package ru.skypro.homework.dto;

import lombok.Data;

/**
 * DTO class for passing data during user authorization
 */
@Data
public class LoginReqDto {
    //password
    private String password;
    //login
    private String username;

}
