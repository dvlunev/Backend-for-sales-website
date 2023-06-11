package ru.skypro.homework.dto;

import lombok.Data;

/**
 * DTO class for passing data during user registration
 */
@Data
public class RegisterReqDto {
    //login
    private String username;
    //password
    private String password;
    //username
    private String firstName;
    //user`s surname
    private String lastName;
    //user`s phone
    private String phone;
    //user`s role
    private Role role;
}
