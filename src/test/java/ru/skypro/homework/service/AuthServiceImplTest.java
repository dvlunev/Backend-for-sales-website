package ru.skypro.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.exception.UserUnauthorizedException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;
import ru.skypro.homework.service.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    private final String username = "testUser";
    private final String password = "testPassword";
    private final String encryptedPassword = "encryptedPassword";
    private final Role role = Role.USER;

    @Test
    @DisplayName("Проверка авторизации, когда учетные данные действительны")
    public void loginValidCredentialsTrueTest() {
        ru.skypro.homework.entity.User user = new ru.skypro.homework.entity.User();
        user.setUsername(username);
        user.setPassword(encryptedPassword);

        when(userService.loadUserByUsername(username))
                .thenReturn(new org.springframework.security.core.userdetails.User(
                username, encryptedPassword, new ArrayList<>()));
        when(encoder.matches(password, encryptedPassword)).thenReturn(true);

        boolean result = authService.login(username, password);

        assertTrue(result);

        verify(userService, times(1)).loadUserByUsername(username);
        verify(encoder, times(1)).matches(password, encryptedPassword);
    }

    @Test
    @DisplayName("Проверка авторизации, когда учетные данные не действительны")
    public void loginInvalidCredentialsFalseTest() {
        ru.skypro.homework.entity.User user = new ru.skypro.homework.entity.User();
        user.setUsername(username);
        user.setPassword(encryptedPassword);

        when(userService.loadUserByUsername(username))
                .thenReturn(new org.springframework.security.core.userdetails.User(
                username, encryptedPassword, new ArrayList<>()));
        when(encoder.matches(password, encryptedPassword)).thenReturn(false);

        boolean result = authService.login(username, password);

        assertFalse(result);

        verify(userService, times(1)).loadUserByUsername(username);
        verify(encoder, times(1)).matches(password, encryptedPassword);
    }

    @Test
    @DisplayName("Проверка успешности регистрации")
    void registerSuccessfulTest() {
        RegisterReqDto registerReqDto = new RegisterReqDto();
        registerReqDto.setUsername(username);
        registerReqDto.setPassword(password);

        ru.skypro.homework.entity.User regUser = new ru.skypro.homework.entity.User();
        regUser.setEmail(username);
        regUser.setPassword(password);
        regUser.setRole(role);

        when(userRepository.findByEmail(username)).thenReturn(Optional.empty());
        when(userMapper.mapToUser(registerReqDto)).thenReturn(regUser);
        when(encoder.encode(password)).thenReturn(encryptedPassword);

        assertTrue(authService.register(registerReqDto, role));

        verify(userRepository, times(1)).findByEmail(username);
        verify(userMapper, times(1)).mapToUser(registerReqDto);
        verify(encoder, times(1)).encode(password);
    }

    @Test
    @DisplayName("Проверка невозможности регистрации, когда пользователь уже зарегистрирован")
    void registerUserAlreadyExistsTest() {
        RegisterReqDto registerReqDto = new RegisterReqDto();
        registerReqDto.setUsername(username);
        registerReqDto.setPassword(password);

        ru.skypro.homework.entity.User existingUser = new ru.skypro.homework.entity.User();
        existingUser.setEmail(username);

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(existingUser));

        assertFalse(authService.register(registerReqDto, role));

        verify(userRepository, times(1)).findByEmail(username);
    }

    @Test
    @DisplayName("Проверка смены пароля, когда пароль из базы данных совпадает с паролем из токена")
    void changePasswordTest() {
        String newPassword = "newPassword";
        NewPasswordDto newPasswordDto = new NewPasswordDto();
        newPasswordDto.setCurrentPassword(password);
        newPasswordDto.setNewPassword(newPassword);

        UserDetails userDetails = new User(username, password, new ArrayList<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        String name = userDetails.getUsername();
        String pas = userDetails.getPassword();

        ru.skypro.homework.entity.User user = new ru.skypro.homework.entity.User();
        user.setUsername(username);

        when(userRepository.findByEmail(user.getUsername())).thenReturn(Optional.of(user));
        when(userService.loadUserByUsername(name)).thenReturn(userDetails);

        when(encoder.matches(password, pas)).thenReturn(true);

        authService.changePassword(newPasswordDto);

        verify(userRepository, times(1)).findByEmail(user.getUsername());
        verify(userService, times(1)).loadUserByUsername(name);
        verify(encoder, times(1)).matches(password, pas);
    }

    @Test
    @DisplayName("Проверка невозможности смены пароля, когда пароль из базы данных не совпадает с паролем из токена")
    void changePasswordWhenPasswordsNotEqualsTest() {
        String newPassword = "newPassword";
        NewPasswordDto newPasswordDto = new NewPasswordDto();
        newPasswordDto.setCurrentPassword(password);
        newPasswordDto.setNewPassword(newPassword);

        UserDetails userDetails = new User(username, password, new ArrayList<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        String name = userDetails.getUsername();
        String pas = userDetails.getPassword();

        ru.skypro.homework.entity.User user = new ru.skypro.homework.entity.User();
        user.setUsername(username);

        when(userRepository.findByEmail(user.getUsername())).thenReturn(Optional.of(user));
        when(userService.loadUserByUsername(name)).thenReturn(userDetails);

        when(encoder.matches(password, pas)).thenReturn(false);

        assertThrows(UserUnauthorizedException.class, () -> authService.changePassword(newPasswordDto));

        verify(userRepository, times(1)).findByEmail(user.getUsername());
        verify(userService, times(1)).loadUserByUsername(name);
        verify(encoder, times(1)).matches(password, pas);
    }
}
