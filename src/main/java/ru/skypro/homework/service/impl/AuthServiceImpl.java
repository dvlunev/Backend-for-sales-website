package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.mapper.UserMapper;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserServiceImpl manager,
                           UserRepository userRepository,
                           UserMapper userMapper) {
        this.manager = manager;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean login(String userName, String password) {
        if (userRepository.findByEmail(userName).isEmpty()) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        return encoder.matches(password, encryptedPassword);
    }

    @Override
    public boolean register(RegisterReqDto registerReqDto, Role role) {
        if (userRepository.findByEmail(registerReqDto.getUsername()).isPresent()) {
            return false;
        }
        ru.skypro.homework.entity.User regUser = userMapper.mapToUser(registerReqDto);
        regUser.setRole(role);
        regUser.setPassword(encoder.encode(regUser.getPassword()));
        userRepository.save(regUser);
        return true;
    }

    @Override
    public void changePassword(NewPasswordDto newPasswordDto, String email) {//не знаю, почему здесь доп. строка и что из двух вариантов оставить
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String currentEmail = principal.getUsername();
        User user = userRepository.findByEmail(currentEmail).orElseThrow(UserNotFoundException::new);
        user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
            userRepository.save(user);
        }
    }
