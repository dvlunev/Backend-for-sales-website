package ru.skypro.homework.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.exception.UserUnauthorizedException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.UserServiceImpl;
import ru.skypro.homework.service.mapper.UserMapper;

/**
 * Интерфейс сервисного класса AuthServiceImpl
 *
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.AuthServiceImpl
 */
public interface AuthService {
    /**
     * Метод производит авторизацию пользователя в системе {@link UserServiceImpl#findAuthUser()}
     *
     * @param userName
     * @param password
     * @return {@link PasswordEncoder#matches(CharSequence, String)}
     */
    boolean login(String userName, String password);

    /**
     * Метод регистрирует пользователя в системе:
     * {@link UserMapper#mapToUser(UserDto)}, {@link PasswordEncoder#encode(CharSequence)}
     *
     * @param registerReqDto
     * @param role
     * @return {@link UserRepository#save(Object)}
     * @see UserMapper
     */
    boolean register(RegisterReqDto registerReqDto, Role role);

    /**
     * Метод меняет пароль {@link PasswordEncoder#encode(CharSequence)}
     *
     * @param newPasswordDto
     * @throws UserNotFoundException     если пользователь не найден
     * @throws UserUnauthorizedException если пользователь не аутентифицирован и поэтому не имеет права доступа к ресурсу
     */
    void changePassword(NewPasswordDto newPasswordDto);
}
