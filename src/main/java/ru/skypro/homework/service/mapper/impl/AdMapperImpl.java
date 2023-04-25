package ru.skypro.homework.service.mapper.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.service.mapper.AdMapper;

import java.util.Collection;

@Component
public class AdMapperImpl implements AdMapper {

    public AdsDto mapAdToAdDto(Ad ad) {

        AdsDto adsDto = new AdsDto();
        adsDto.setId(ad.getId());
        adsDto.setAuthor(ad.getAuthor().getId());
        adsDto.setPrice(ad.getPrice());
        adsDto.setImage(ad.getImage().getImageLink());
        adsDto.setTitle(ad.getTitle());
        return adsDto;
    }

    public Ad mapAdsDtoToAd(AdsDto adsDto) {
        Ad mappedAd = new Ad();
        mappedAd.setId(adsDto.getId());
        mappedAd.getAuthor().setId(adsDto.getAuthor());
        mappedAd.setPrice(adsDto.getPrice());
        mappedAd.getImage().setImageLink(adsDto.getImage());
        mappedAd.setTitle(adsDto.getTitle());
        return mappedAd;
    }

    @Override
    public FullAdsDto mapAdToFullAdsDTo(Ad ad) {
        FullAdsDto fullAdsDto = new FullAdsDto();
        fullAdsDto.setId(ad.getId());
        fullAdsDto.setAuthorFirstName(ad.getAuthor().getFirstName());
        fullAdsDto.setAuthorLastName(ad.getAuthor().getLastName());
        fullAdsDto.setEmail(ad.getAuthor().getEmail());
        fullAdsDto.setPhone(ad.getAuthor().getPhone());
        fullAdsDto.setTitle(ad.getTitle());
        fullAdsDto.setDescription(ad.getDescription());
        fullAdsDto.setImage(ad.getImage().getImageLink());
        fullAdsDto.setPrice(ad.getPrice());
        return fullAdsDto;
    }

    @Override
    public Ad mapCreatedAdsDtoToAd(CreateAdsDto createAdsDto) {
        Ad ad = new Ad();
        ad.setTitle(createAdsDto.getTitle());
        ad.setDescription(createAdsDto.getDescription());
        ad.setPrice(createAdsDto.getPrice());
        return ad;
    }

    @Override
    public Collection<AdsDto> mapAdListToAdDtoList(Collection<Ad> adCollection) {
        return null;
    }
}