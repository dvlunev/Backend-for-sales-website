package ru.skypro.homework.service.mapper.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.mapper.UserMapper;

@Component
public class UserMapperImpl implements UserMapper {

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getImage().getImageLink()
        );
        return userDto;
    }

    public User mapToUser(UserDto userDto) {
        User mappedUser = new User();
        mappedUser.setId(userDto.getId());
        mappedUser.setEmail(userDto.getEmail());
        mappedUser.setFirstName(userDto.getFirstName());
        mappedUser.setLastName(userDto.getLastName());
        mappedUser.setPhone(userDto.getPhone());
        mappedUser.getImage().setImageLink(userDto.getImage());
        return mappedUser;
    }

    @Override
    public User mapToUser(RegisterReqDto registerReqDto) {
        User mappedUser = new User();
        mappedUser.setUsername(registerReqDto.getUsername());
        mappedUser.setPassword(registerReqDto.getPassword());
        mappedUser.setFirstName(registerReqDto.getFirstName());
        mappedUser.setLastName(registerReqDto.getLastName());
        mappedUser.setPhone(registerReqDto.getPhone());
        mappedUser.setRole(registerReqDto.getRole());
        mappedUser.setEmail("default@gmail.com");
        return mappedUser;
    }
}
