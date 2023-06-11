package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mapper.UserMapper;

import java.util.Optional;

/**
 * Service class interface UserServiceImpl containing a set of CRUD operations with the User object
 *
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.UserServiceImpl
 */
public interface UserService {
    /**
     * The method looks for an authorized User
     *
     * @return {@link UserRepository#findByEmail(String)}
     */
    Optional<User> findAuthUser();

    /**
     * The method gets a User from the database {@link UserService#findAuthUser()}
     * and converts it to {@link UserDto}
     *
     * @return {@link UserMapper#mapToUser(UserDto)}
     * @see UserMapper
     */
    UserDto getUserDto();

    /**
     * The method gets a User from the database {@link UserService#findAuthUser()},
     * edits the data and saves in the database
     *
     * @param newUserDto
     * @return {@link UserRepository#save(Object)}, {@link UserMapper#mapToUser(UserDto)}
     * @see UserMapper
     */
    UserDto updateUserDto(UserDto newUserDto);

    /**
     * The method gets a User from the database {@link UserService#findAuthUser()},
     * sets or updates his image, then saves the changes in the database:
     * {@link ImageRepository#saveAndFlush(Object)}, {@link UserRepository#save(Object)}
     *
     * @param image
     * @throws UsernameNotFoundException if the User is not found
     */
    void updateUserImage(MultipartFile image);
}
