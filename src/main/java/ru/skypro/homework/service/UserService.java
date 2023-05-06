package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
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

    boolean isAuth(Authentication authentication);

    /**
     * Метод ищет авторизованного пользователя
     * @return Optional<User>
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<User> findAuthUser();


    UserDto updateUserDto(UserDto newUserDto, Authentication authentication);

    /**
     * Метод проверяет совпадает ли пароль авторизованного пользователя с {@link ru.skypro.homework.dto.NewPasswordDto#currentPassword}
     * @param newPasswordDto
     * @param email e-mail авторизованного пользователя
     * @return <b>true</b> если {@link ru.skypro.homework.dto.NewPasswordDto#currentPassword} совпадает с паролем авторизованного пользователя
     *@see ru.skypro.homework.service.impl.UserServiceImpl
     */
    boolean isCurrentPassTrue(NewPasswordDto newPasswordDto, String email);

    /**
     * Метод обновляет пароль пользователя
     * @param newPasswordDto
     * @param email e-mail авторизованного пользователя
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    void changePassword(NewPasswordDto newPasswordDto, String email);

    /**
     * Метод ищет и возвращает пользователя по id
     * @param id
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<UserDto> getById(Long id);


    /**
     * Метод возвращает Dto авторизованного пользователя
     *
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    UserDto getUserDto(Authentication authentication);

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
     * @param image
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    void updateUserImage(MultipartFile image);

}
