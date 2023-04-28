package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * Интерфейс сервисного класса UserServiceImpl, содержащий набор CRUD операций над объектом User
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.UserServiceImpl
 */
public interface UserService {

    /**
     * Метод проверяет авторизован ли пользователь
     * @return boolean
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    boolean isAuth();

    /**
     * Метод ищет авторизованного пользователя
     * @return Optional<User>
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<User> findAuthUser();


    /**
     * Метод обновляет пароль пользователя
     * @param newPasswordDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    boolean setNewPasswordDto(NewPasswordDto newPasswordDto);


    /**
     * Метод возвращает Dto авторизованного пользователя
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<UserDto> getUserDto();

    /**
     * Метод ищет и возвращает пользователя по id
     * @param id
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<UserDto> getById(Long id);


    /**
     * Метод редактирует данные авторизованного пользователя
     *
     * @param userDto
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<UserDto> updateUserDto(UserDto userDto);

    /**
     * Метод обновляет аватар пользователя
     * @param image
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    void updateUserImage(MultipartFile image);
}
