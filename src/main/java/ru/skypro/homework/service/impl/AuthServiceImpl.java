package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.exception.UserUnauthorizedException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.mapper.UserMapper;

/**
 * The service class containing the implementation of the interface {@link AuthService}
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * The method authorizes a user in the system {@link UserServiceImpl#findAuthUser()}
     *
     * @param userName
     * @param password
     * @return {@link PasswordEncoder#matches(CharSequence, String)}
     */
    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        return encoder.matches(password, encryptedPassword);
    }

    /**
     * The method registers the user in the system:
     * {@link UserMapper#mapToUser(UserDto)}, {@link PasswordEncoder#encode(CharSequence)}
     *
     * @param registerReqDto
     * @param role
     * @return {@link UserRepository#save(Object)}
     * @see UserMapper
     */
    @Override
    public boolean register(RegisterReqDto registerReqDto, Role role) {
        if (userRepository.findByEmail(registerReqDto.getUsername()).isPresent()) {
            return false;
        }
        User regUser = userMapper.mapToUser(registerReqDto);
        regUser.setRole(role);
        regUser.setPassword(encoder.encode(regUser.getPassword()));
        userRepository.save(regUser);
        return true;
    }

    /**
     * The method changes the password {@link PasswordEncoder#encode(CharSequence)}
     *
     * @param newPasswordDto
     * @throws UserNotFoundException     if the user is not found
     * @throws UserUnauthorizedException if the user is not authenticated and therefore does not have the right to access the resource
     */
    @Override
    public void changePassword(NewPasswordDto newPasswordDto) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String currentEmail = principal.getUsername();
        User user = userRepository.findByEmail(currentEmail).orElseThrow(UserNotFoundException::new);
        UserDetails userDetails = manager.loadUserByUsername(user.getUsername());
        String encryptedPassword = userDetails.getPassword();
        if (encoder.matches(newPasswordDto.getCurrentPassword(), encryptedPassword)) {
            user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new UserUnauthorizedException();
        }
    }
}
