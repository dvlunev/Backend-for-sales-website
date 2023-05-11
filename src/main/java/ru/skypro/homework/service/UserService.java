package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * Интерфейс сервисного класса UserServiceImpl, содержащий набор CRUD операций над объектом User
 *
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.UserServiceImpl
 */
public interface UserService {
    /**
     * Метод ищет авторизованного пользователя
     *
     * @return Optional<User>
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<User> findAuthUser();

    /**
     * Метод возвращает Dto авторизованного пользователя
     *
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    UserDto getUserDto();

    /**
     * Метод редактирует данные авторизованного пользователя
     *
     * @param userDto
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    UserDto updateUserDto(UserDto userDto);

    /**
     * Метод обновляет аватар пользователя
     *
     * @param image
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Image updateUserImage(MultipartFile image);
}
