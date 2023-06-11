package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

/**
 * UserMapperImpl service class interface for mapping User, UserDto and RegisterReqDto objects
 *
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.dto.UserDto
 * @see ru.skypro.homework.dto.RegisterReqDto
 * @see ru.skypro.homework.service.mapper.impl.UserMapperImpl
 */
public interface UserMapper {
    /**
     * The method that converts an object of the User class into an object of the UserDto class.
     *
     * @param user
     * @return UserDto
     */
    UserDto mapToUserDto(User user);

    /**
     * The method that converts an object of the UserDto class into an object of the User class.
     *
     * @param userDto
     * @return User
     */
    User mapToUser(UserDto userDto);

    /**
     * The method that converts an object of the RegisterReqDto class into an object of the User class.
     *
     * @param registerReqDto
     * @return User
     */
    User mapToUser(RegisterReqDto registerReqDto);
}
