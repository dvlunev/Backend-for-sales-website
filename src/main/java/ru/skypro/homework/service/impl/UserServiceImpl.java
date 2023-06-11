package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.UserMapper;

import java.util.Optional;

/**
 * The service class containing the implementation of the interface {@link UserService} и {@link UserDetailsService}
 *
 * @see User
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserMapper userMapper;
    private final ImageService imageService;
    private final UserRepository userRepository;

    /**
     * The method searches a user by email and returns his data: username and password
     *
     * @param username
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException if the user is not found
     * @see UserDetailsService
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + username + " doesn't exists"));
    }

    /**
     * The method searches for an authorized user
     *
     * @return {@link UserRepository#findByEmail(String)}
     */
    @Override
    public Optional<User> findAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(currentPrincipalName);
    }

    /**
     * The method gets the user from the database {@link UserService#findAuthUser()} and converts it to {@link UserDto}
     *
     * @return {@link UserMapper#mapToUser(UserDto)}
     * @see UserMapper
     */
    @Override
    public UserDto getUserDto() {
        Optional<User> currentUser = findAuthUser();
        UserDto currentUserDto = new UserDto();
        if (currentUser.isPresent()) {
            currentUserDto = userMapper.mapToUserDto(currentUser.get());
        }
        return currentUserDto;
    }

    /**
     * The method gets the user from the database {@link UserService#findAuthUser()},
     * edits a data and saves in the database
     *
     * @param newUserDto
     * @return {@link UserRepository#save(Object)}, {@link UserMapper#mapToUser(UserDto)}
     * @see UserMapper
     */
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

    /**
     * The method gets the user from the database {@link UserService#findAuthUser()},
     * sets or updates his image, than saves the changes to the database:
     * {@link ImageRepository#saveAndFlush(Object)}, {@link UserRepository#save(Object)}
     *
     * @param image
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public void updateUserImage(MultipartFile image) {
        User user = findAuthUser().orElseThrow(UserNotFoundException::new);
        Image oldImage = user.getImage();
        if (oldImage == null) {
            Image newImage = imageService.saveImage(image);
            user.setImage(newImage);
        } else {
            Image updatedImage = imageService.updateImage(image, oldImage);
            user.setImage(updatedImage);
        }
        userRepository.save(user);
    }
}
