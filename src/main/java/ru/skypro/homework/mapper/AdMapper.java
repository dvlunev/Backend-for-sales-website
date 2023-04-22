package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ad;

public class AdMapper {

    public static AdsDto mapToUserDto(Ad ad) {

        AdsDto adsDto = new AdsDto();

        adsDto.setId(ad.getId());
        adsDto.setAuthor(ad.getAuthor().getId());
        adsDto.setPrice(ad.getPrice());
        adsDto.setImage(ad.getImage().getImageLink());
        adsDto.setTitle(ad.getTitle());
        return adsDto;
    }

    public static Ad mapToUserDto(AdsDto adsDto) {
        Ad mappedAd = new Ad();
        mappedAd.setId(adsDto.getId());
        mappedAd.getAuthor().setId(adsDto.getAuthor());
        mappedAd.setPrice(adsDto.getPrice());
        mappedAd.getImage().setImageLink(adsDto.getImage());
        mappedAd.setTitle(adsDto.getTitle());
        return mappedAd;
    }
}