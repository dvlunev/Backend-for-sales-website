package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.mapper.UserMapper;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public AuthServiceImpl(UserDetailsManager manager,
                           UserRepository userRepository,
                           UserMapper userMapper) {
        this.manager = manager;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean login(String userName, String password) {
        if (!userRepository.findByEmail(userName).isPresent()) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }

    @Override
    public boolean register(RegisterReqDto registerReqDto, Role role) {
        if (manager.userExists(registerReqDto.getUsername())) {
            return false;
        }
        manager.createUser(
                User.withDefaultPasswordEncoder()
                        .password(registerReqDto.getPassword())
                        .username(registerReqDto.getUsername())
                        .roles(role.name())
                        .build()
        );
        ru.skypro.homework.entity.User regUser = userMapper.mapToUser(registerReqDto);
        regUser.setRole(role);
        userRepository.save(regUser);
        return true;
    }



}
