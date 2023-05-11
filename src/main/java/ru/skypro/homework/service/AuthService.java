package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;

/**
 * Интерфейс сервисного класса AuthServiceImpl
 *
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.AuthServiceImpl
 */
public interface AuthService {
    /**
     * Метод авторизует пользователя в системе
     *
     * @param userName
     * @param password
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    boolean login(String userName, String password);

    /**
     * Метод регистрирует пользователя в системе
     *
     * @param registerReqDto
     * @param role
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    boolean register(RegisterReqDto registerReqDto, Role role);

    /**
     * Метод обновляет пароль пользователя
     *
     * @param newPasswordDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    void changePassword(NewPasswordDto newPasswordDto);
}
