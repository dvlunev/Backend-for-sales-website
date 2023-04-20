package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface UserMapper {
//    ImageMapper MAPPER = Mappers.getMapper(ImageMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
