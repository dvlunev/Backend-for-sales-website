package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Image;

public interface AdService {
    ResponseWrapperAdsDto getAllAdsDto();
    AdsDto createdAdDto(CreateAdsDto adDto, Image image);
    FullAdsDto getFullAdDto(Long id);
    boolean removeAdDto(Long id);
    AdsDto updateAdDto(Long id, CreateAdsDto adDto);
    ResponseWrapperAdsDto getAllMyAdsDto();
}
