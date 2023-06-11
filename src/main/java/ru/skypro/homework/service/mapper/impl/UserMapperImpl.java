package ru.skypro.homework.service.mapper.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.mapper.UserMapper;

import java.util.Optional;

/**
 * Service-mapper class containing the implementation of the interface {@link UserMapper}
 */
@Component
public class UserMapperImpl implements UserMapper {

    /**
     * The method that converts an object of the User class into an object of the UserDto class.
     *
     * @param user
     * @return UserDto
     */
    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        Optional.ofNullable(user.getImage()).ifPresent(image -> userDto.setImage(
                "/users/" + user.getImage().getId() + "/image"));
        return userDto;
    }

    /**
     * The method that converts an object of the UserDto class into an object of the User class.
     *
     * @param userDto
     * @return User
     */
    public User mapToUser(UserDto userDto) {
        User mappedUser = new User();
        mappedUser.setId(userDto.getId());
        mappedUser.setEmail(userDto.getEmail());
        mappedUser.setFirstName(userDto.getFirstName());
        mappedUser.setLastName(userDto.getLastName());
        mappedUser.setPhone(userDto.getPhone());
        mappedUser.getImage().setId(userDto.getImage());
        return mappedUser;
    }

    /**
     * The method that converts an object of the RegisterReqDto class into an object of the User class.
     *
     * @param registerReqDto
     * @return User
     */
    @Override
    public User mapToUser(RegisterReqDto registerReqDto) {
        User mappedUser = new User();
        mappedUser.setUsername(registerReqDto.getUsername());
        mappedUser.setPassword(registerReqDto.getPassword());
        mappedUser.setFirstName(registerReqDto.getFirstName());
        mappedUser.setLastName(registerReqDto.getLastName());
        mappedUser.setPhone(registerReqDto.getPhone());
        mappedUser.setRole(registerReqDto.getRole());
        mappedUser.setEmail(registerReqDto.getUsername());
        //в форме регистрации username и email одно и тоже
        return mappedUser;
    }
}
