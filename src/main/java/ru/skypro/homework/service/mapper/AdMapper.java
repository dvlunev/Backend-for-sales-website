package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.entity.Ad;

import java.util.Collection;

public interface AdMapper {

    AdsDto mapToAdDto(Ad ad);


    Ad mapToAdDto(AdsDto adsDto);

    Ad createdAdsDtoToAd(CreateAdsDto createAdsDto);
    Collection<AdsDto> entityToDtoList(Collection<Ad> adCollection);

}

