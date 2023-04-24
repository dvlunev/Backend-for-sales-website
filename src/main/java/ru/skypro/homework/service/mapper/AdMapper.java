package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;

import java.util.Collection;

public interface AdMapper {

    AdsDto mapToAdDto(Ad ad);


    Ad mapToAdDto(AdsDto adsDto);
    FullAdsDto adEntityToFullAdsDTo(Ad ad);
    Ad createdAdsDtoToAd(CreateAdsDto createAdsDto);
    Collection<AdsDto> adEntityToDtoList(Collection<Ad> adCollection);

}

