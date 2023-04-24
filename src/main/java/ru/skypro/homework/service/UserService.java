package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;

import java.util.Optional;

/**
 * Интерфейс сервисного класса UserServiceImpl, содержащий набор CRUD операций над объектом User
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.UserServiceImpl
 */
public interface UserService {
    /**
     * Метод обновляет пароль пользователя
     * @param currentPassword
     * @param newPassword
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    /* часть со сменой пароля возможно нужно перенести в AuthService и оставить один метод из двух */
    boolean setNewPasswordDto(String currentPassword, String newPassword);
    boolean setNewPasswordDto(NewPasswordDto newPasswordDto);

    /**
     * Метод определяет уровень доступа пользователя
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    boolean userIsAdmin();

    /**
     * Метод ищет и возвращает пользователя
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<UserDto> getUserDto();

    /**
     * Метод ищет и возвращает пользователя по id
     * @param id
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<UserDto> getById(Long id);

    /**
     * Метод ищет и возвращает пользователя по userName
     * @param userName
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    Optional<UserDto> getByUserName(String userName);

    /**
     * Метод редактирует пользователя
     * @param userDto
     * @return UserDto
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    UserDto updateUserDto(UserDto userDto);

    /**
     * Метод обновляет аватар пользователя
     * @param image
     * @see ru.skypro.homework.service.impl.UserServiceImpl
     */
    void updateUserImage(MultipartFile image);
}
