package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean setNewPasswordDto(String currentPassword, String newPassword) {
        return false;
    }

    @Override
    public boolean setNewPasswordDto(NewPasswordDto newPasswordDto) {
        return false;
    }

    @Override
    public boolean userIsAdmin() {
        return false;
    }

    @Override
    public Optional<UserDto> getUserDto() {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> getByUserName(String userName) {
        return Optional.empty();
    }

    @Override
    public UserDto updateUserDto(UserDto userDto) {
        return null;
    }

    @Override
    public void updateUserImage(MultipartFile image) {

    }
}
