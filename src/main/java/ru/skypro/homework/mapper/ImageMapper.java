package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {
//    String MAPPER = Mappers.getMapper(String.class);

    String mapToImageString(Image image);

    Image mapToAd(String image);
}
