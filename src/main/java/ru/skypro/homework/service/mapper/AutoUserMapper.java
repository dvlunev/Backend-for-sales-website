package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

//@Mapper(componentModel = "spring",uses = Image.class)
public interface AutoUserMapper {
    @Mapping(target = "image", source = "image",qualifiedByName = "imageToString")
    UserDto mapToUserDto(User user);

    @Named("imageToString")
    String imageToString(Image image);

    @Mapping(target = "image", source = "image",qualifiedByName = "stringToImage")
    User mapToUser(UserDto userDto);
    @Named("stringToImage")
    Image stringToImage(String imageLink);

    User regUserMapToUser(RegisterReqDto registerReqDto);
}
