package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.UserMapper;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean isAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated();
    }

    @Override
    public Optional<User> findAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(currentPrincipalName);
    }


    @Override
    public Optional<UserDto> getUserDto() {
        Optional<User> currentUser = findAuthUser();
        if (currentUser.isPresent()) {
            UserDto currentUserDto = userMapper.mapToUserDto(currentUser.get());
            return Optional.of(currentUserDto);
        } else
            return Optional.empty();
        //не уверена, что возможен вариант, когда информация об авторизованном пользователе не найдена
        //если currentUserDto не может быть null, то Optional нужно убрать
    }

    @Override
    public Optional<UserDto> updateUserDto(UserDto newUserDto) {
        Optional<User> currentUser = findAuthUser();
        if (currentUser.isPresent()) {
            User userFromDB = userRepository.findById(currentUser.get().getId()).get();
            userFromDB.setFirstName(newUserDto.getFirstName());
            userFromDB.setLastName(newUserDto.getLastName());
            userFromDB.setPhone(newUserDto.getPhone());
            //другие поля остаются теми же
            userRepository.save(userFromDB);
            UserDto currentUserDto = userMapper.mapToUserDto(userFromDB);
            return Optional.of(currentUserDto);
        } else
            return Optional.empty();
    }


    @Override
    public boolean setNewPasswordDto(NewPasswordDto newPasswordDto) {
        return false;
    }


    @Override
    public Optional<UserDto> getById(Long id) {
        return Optional.empty();
    }


    @Override
    public void updateUserImage(MultipartFile image) {

    }
}
