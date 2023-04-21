package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;

public interface UserService {
    /* часть со сменой пароля возможно нужно перенести в AuthService и оставить один метод из двух */
    boolean setNewPasswordDto(String currentPassword, String newPassword);
    boolean setNewPasswordDto(NewPasswordDto newPasswordDto);
    UserDto getUserDto();
    UserDto updateUserDto(UserDto userDto);
    UserDto updateUserImage(Image image);
}
