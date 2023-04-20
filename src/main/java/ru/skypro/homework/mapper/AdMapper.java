package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.AdsDto;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.entity.Ad;
@Mapper(componentModel = "spring", uses = {UserMapper.class,ImageMapper.class})
public interface AdMapper {
//    AdsDto MAPPER = Mappers.getMapper(AdsDto.class);

    AdsDto mapToAdsDto(Ad ad);

    Ad mapToAd(AdsDto adsDto);
}
