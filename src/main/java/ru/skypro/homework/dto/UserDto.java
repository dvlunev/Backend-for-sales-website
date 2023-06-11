package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for passing full information about the user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    //user id
    private int id;
    //user login
    private String email;
    //username
    private String firstName;
    //user`s surname
    private String lastName;
    //user`s phone
    private String phone;
    //user`s image link
    private String image;
}
