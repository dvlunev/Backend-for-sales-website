package ru.skypro.homework.service.impl;

import liquibase.pro.packaged.O;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
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

    //здесь должно быть получение авторизованного пользователь
   Image currentUserImage = new Image(1, "https://imageLink");
    User currentUser = new User(1, "user@gmail.com", "Winston", "Smith", "+7(000)000-00-00", currentUserImage, "password", "username", Role.USER);

    @Override
    public Optional<UserDto> getUserDto() {
        if (currentUser != null) {
            UserDto currentUserDto = userMapper.mapToUserDto(currentUser);
            return Optional.of(currentUserDto);
        } else
            return Optional.empty();
        //не уверена, что возможен вариант, когда информация об авторизованном пользователе не найдена
        //если currentUserDto не может быть null, то Optional нужно убрать
    }

    @Override
    public Optional<UserDto> updateUserDto(UserDto newUserDto) {
        if (currentUser != null) {
            User userFromDB  = userRepository.findById(currentUser.getId()).get();
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
