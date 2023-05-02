package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;

/**
 * Интерфейс сервисного класса AuthServiceImpl
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.AuthServiceImpl
 */
public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReqDto registerReqDto, Role role);


}
