package ru.skypro.homework.service.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring",uses = {User.class, Image.class})
public interface AutoAdMapper {
//
    @Mapping(target = "image", source = "image",qualifiedByName = "imageToString")
    @Mapping(target = "author",source = "author", qualifiedByName = "userToInt")
    @InheritConfiguration
    AdsDto mapToAdDto(Ad ad);


    @Named("imageToString")
    @Mapping(target = "image", source = "image",qualifiedByName = "imageToString")
    @InheritConfiguration
    String imageToString(Image image);
    @Named("userToInt")
    Integer userToInt(User user);


    Ad mapToAd(AdsDto adsDto);
}
