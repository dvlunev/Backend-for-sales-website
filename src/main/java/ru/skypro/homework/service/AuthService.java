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
 * Service class interface AuthServiceImpl
 *
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.AuthServiceImpl
 */
public interface AuthService {
    /**
     * The method authorizes the user in the system  {@link UserServiceImpl#findAuthUser()}
     *
     * @param userName
     * @param password
     * @return {@link PasswordEncoder#matches(CharSequence, String)}
     */
    boolean login(String userName, String password);

    /**
     * The method registers the user in the system:
     * {@link UserMapper#mapToUser(UserDto)}, {@link PasswordEncoder#encode(CharSequence)}
     *
     * @param registerReqDto
     * @param role
     * @return {@link UserRepository#save(Object)}
     * @see UserMapper
     */
    boolean register(RegisterReqDto registerReqDto, Role role);

    /**
     * The method changes the password {@link PasswordEncoder#encode(CharSequence)}
     *
     * @param newPasswordDto
     * @throws UserNotFoundException     if the uses is not found
     * @throws UserUnauthorizedException if the user is not authenticated and therefore does not have the access to the resource
     */
    void changePassword(NewPasswordDto newPasswordDto);
}
