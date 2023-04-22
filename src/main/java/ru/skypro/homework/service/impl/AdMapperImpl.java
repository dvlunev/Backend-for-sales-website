package ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.service.mapper.AdMapper;

public class AdMapperImpl implements AdMapper {

    public AdsDto mapToAdDto(Ad ad) {

        AdsDto adsDto = new AdsDto();

        adsDto.setId(ad.getId());
        adsDto.setAuthor(ad.getAuthor().getId());
        adsDto.setPrice(ad.getPrice());
        adsDto.setImage(ad.getImage().getImageLink());
        adsDto.setTitle(ad.getTitle());
        return adsDto;
    }

    public Ad mapToAdDto(AdsDto adsDto) {
        Ad mappedAd = new Ad();
        mappedAd.setId(adsDto.getId());
        mappedAd.getAuthor().setId(adsDto.getAuthor());
        mappedAd.setPrice(adsDto.getPrice());
        mappedAd.getImage().setImageLink(adsDto.getImage());
        mappedAd.setTitle(adsDto.getTitle());
        return mappedAd;
    }
}