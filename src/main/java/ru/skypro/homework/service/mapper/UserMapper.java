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
     * Метод для маппинга объекта типа User на объект типа UserDto.
     *
     * @param user
     * @return UserDto
     */
    UserDto mapToUserDto(User user);

    /**
     * Метод для маппинга объекта типа UserDto на объект типа User.
     *
     * @param userDto
     * @return User
     */
    User mapToUser(UserDto userDto);

    /**
     * Метод для маппинга объекта типа RegisterReqDto на объект типа User.
     *
     * @param registerReqDto
     * @return User
     */
    User mapToUser(RegisterReqDto registerReqDto);
}
