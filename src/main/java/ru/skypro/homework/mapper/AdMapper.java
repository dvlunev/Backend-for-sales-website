package ru.skypro.homework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.AdRepository;

public class AdMapper {
    @Autowired
    private static AdRepository adRepository;

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
        if(adRepository.existsById((long) adsDto.getId())){
            mappedAd = adRepository.getReferenceById((long) adsDto.getId());
        }
        mappedAd.getAuthor().setId(adsDto.getAuthor());
        mappedAd.setPrice(adsDto.getPrice());
        mappedAd.getImage().setImageLink(adsDto.getImage());
        mappedAd.setTitle(adsDto.getTitle());
        return mappedAd;
    }
}