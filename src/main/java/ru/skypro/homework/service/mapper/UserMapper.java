package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

/**
 * Интерфейс сервисного класса-маппера UserMapperImpl, для маппинга объектов RegisterReqDto, UserDto и User
 *
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.dto.UserDto
 * @see ru.skypro.homework.dto.RegisterReqDto
 * @see ru.skypro.homework.service.mapper.impl.UserMapperImpl
 */
public interface UserMapper {
    /**
     * Метод, преобразующий объект класса User в объект класса UserDto.
     *
     * @param user
     * @return UserDto
     */
    UserDto mapToUserDto(User user);

    /**
     * Метод, преобразующий объект класса UserDto в объект класса User.
     *
     * @param userDto
     * @return User
     */
    User mapToUser(UserDto userDto);

    /**
     * Метод, преобразующий объект класса RegisterReqDto в объект класса User.
     *
     * @param registerReqDto
     * @return User
     */
    User mapToUser(RegisterReqDto registerReqDto);
}
