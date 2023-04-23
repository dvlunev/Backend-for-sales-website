package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

public interface UserMapper {
    public UserDto mapToUserDto(User user);
    public User mapToUser(UserDto userDto);
}
