package ru.skypro.homework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;


public class UserMapper {
    @Autowired
    private static UserRepository userRepository;

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setImage(user.getImage().getImageLink());
        return userDto;
    }

    public User mapToUser(UserDto userDto) {
        User mappedUser = new User();
        if (userRepository.existsById((long) userDto.getId())) {
            mappedUser = userRepository.getReferenceById((long) userDto.getId());
        }
        mappedUser.setEmail(userDto.getEmail());
        mappedUser.setFirstName(userDto.getFirstName());
        mappedUser.setLastName(userDto.getLastName());
        mappedUser.setPhone(userDto.getPhone());
        mappedUser.getImage().setImageLink(userDto.getImage());
        userRepository.save(mappedUser);
        return mappedUser;
    }
}
