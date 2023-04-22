package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ad;

public interface AdMapper {

    AdsDto mapToAdDto(Ad ad);


    Ad mapToAdDto(AdsDto adsDto);

}

