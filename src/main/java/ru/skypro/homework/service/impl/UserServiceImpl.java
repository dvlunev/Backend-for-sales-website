package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.UserMapper;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final UserDetailsManager manager;


    public UserServiceImpl(UserMapper userMapper,
                           UserRepository userRepository,
                           ImageRepository imageRepository,
                           UserDetailsManager manager) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.manager = manager;
    }

    @Override
    public boolean isAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated();
    }

    @Override
    public boolean isAuth(Authentication authentication) {
        return authentication.isAuthenticated();
    }

    @Override
    public Optional<User> findAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(currentPrincipalName);
    }
    @Override
    public boolean isCurrentPassTrue(NewPasswordDto newPasswordDto, String email) {
        Optional<User> currentUser = userRepository.findByEmail(email);
        if (currentUser.isPresent()) {
            User currentUserValue = currentUser.get();
            return (currentUserValue.getPassword().equals(newPasswordDto.getCurrentPassword()));
        }
        return false;
    }

    @Override
    public void changePassword(NewPasswordDto newPasswordDto, String email) {
        Optional<User> userFromDB = userRepository.findByEmail(email);
        userFromDB.ifPresent(user -> {
            user.setPassword(newPasswordDto.getNewPassword());
            userRepository.save(user);
        });
    }

    @Override
    public UserDto getUserDto(Authentication authentication) {
        UserDto currentUserDto = new UserDto();
        Optional<User> currentUser = userRepository.findByEmail(authentication.getName());
        if (currentUser.isPresent()) {
            currentUserDto = userMapper.mapToUserDto(currentUser.get());
        }
        return currentUserDto;
    }

    @Override
    public UserDto updateUserDto(UserDto newUserDto) {
        Optional<User> currentUser = findAuthUser();
        User newCurrentUser = new User();
        if (currentUser.isPresent()) {
            newCurrentUser = currentUser.get();
            newCurrentUser.setFirstName(newUserDto.getFirstName());
            newCurrentUser.setLastName(newUserDto.getLastName());
            newCurrentUser.setPhone(newUserDto.getPhone());
            //другие поля остаются теми же
            userRepository.save(newCurrentUser);
        }
        return userMapper.mapToUserDto(newCurrentUser);
    }

    @Override
    public UserDto updateUserDto(UserDto newUserDto, Authentication authentication) {
        User newCurrentUser = new User();
        Optional<User> currentUser = userRepository.findByEmail(authentication.getName());
        if (currentUser.isPresent()) {
            newCurrentUser = currentUser.get();
            newCurrentUser.setFirstName(newUserDto.getFirstName());
            newCurrentUser.setLastName(newUserDto.getLastName());
            newCurrentUser.setPhone(newUserDto.getPhone());
            //другие поля остаются теми же
            userRepository.save(newCurrentUser);
        }
        return userMapper.mapToUserDto(newCurrentUser);
    }


    @Override
    public Optional<UserDto> getById(Long id) {
        return Optional.empty();
    }


    @Override
    public void updateUserImage(MultipartFile image) {
        User user = findAuthUser().orElseThrow(UserNotFoundException::new);
        Image oldImage = user.getImage();
        if (oldImage == null) {
            Image newImage = new Image();
            try {
                byte[] bytes = image.getBytes();
                newImage.setImagePath(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newImage.setId(UUID.randomUUID().toString());
            Image savedImage = imageRepository.saveAndFlush(newImage);
            user.setImage(savedImage);
        } else {
            try {
                byte[] bytes = image.getBytes();
                oldImage.setImagePath(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Image savedImage = imageRepository.saveAndFlush(oldImage);
            user.setImage(savedImage);
        }
        userRepository.save(user);
    }
}
