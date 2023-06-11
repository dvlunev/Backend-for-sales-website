package ru.skypro.homework.service.mapper.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.service.mapper.AdMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Service-mapper class containing the implementation of the interface {@link AdMapper}
 */
@Component
public class AdMapperImpl implements AdMapper {

    /**
     * The method that converts an object of the Ad class into an object of the AdDto class.
     *
     * @param ad
     * @return AdsDto
     */
    @Override
    public AdsDto mapAdToAdDto(Ad ad) {
        AdsDto adsDto = new AdsDto();
        adsDto.setPk(ad.getId());
        adsDto.setAuthor(ad.getAuthor().getId());
        adsDto.setPrice(ad.getPrice());
        adsDto.setImage("/ads/" + ad.getImage().getId() + "/image");
        adsDto.setTitle(ad.getTitle());
        return adsDto;
    }

    /**
     * The method that converts an object of the AdsDto class into an object of the Ad class.
     *
     * @param adsDto
     * @return Ad
     */
    @Override
    public Ad mapAdsDtoToAd(AdsDto adsDto) {
        Ad mappedAd = new Ad();
        mappedAd.setId(adsDto.getPk());
        mappedAd.getAuthor().setId(adsDto.getAuthor());
        mappedAd.setPrice(adsDto.getPrice());
        mappedAd.getImage().setId(adsDto.getImage());
        mappedAd.setTitle(adsDto.getTitle());
        return mappedAd;
    }

    /**
     * The method that converts an object of the Ad class into an object of the FullAdsDTo class.
     *
     * @param ad
     * @return FullAdsDTo
     */
    @Override
    public FullAdsDto mapAdToFullAdsDTo(Ad ad) {
        FullAdsDto fullAdsDto = new FullAdsDto();
        fullAdsDto.setPk(ad.getId());
        fullAdsDto.setAuthorFirstName(ad.getAuthor().getFirstName());
        fullAdsDto.setAuthorLastName(ad.getAuthor().getLastName());
        fullAdsDto.setEmail(ad.getAuthor().getEmail());
        fullAdsDto.setPhone(ad.getAuthor().getPhone());
        fullAdsDto.setTitle(ad.getTitle());
        fullAdsDto.setDescription(ad.getDescription());
        fullAdsDto.setImage("/ads/" + ad.getImage().getId() + "/image");
        fullAdsDto.setPrice(ad.getPrice());
        return fullAdsDto;
    }

    /**
     * The method that converts an object of the CreateAdsDto class into an object of the Ad class.
     *
     * @param createAdsDto
     * @return Ad
     */
    @Override
    public Ad mapCreatedAdsDtoToAd(CreateAdsDto createAdsDto) {
        Ad ad = new Ad();
        ad.setTitle(createAdsDto.getTitle());
        ad.setDescription(createAdsDto.getDescription());
        ad.setPrice(createAdsDto.getPrice());
        return ad;
    }

    /**
     * The method that converts a Collection of the Ad classes into a Collection of the AdsDto classes.
     *
     * @param adCollection
     * @return Collection<AdsDto>
     */
    @Override
    public Collection<AdsDto> mapAdListToAdDtoList(Collection<Ad> adCollection) {
        List<AdsDto> dtoList = new ArrayList<AdsDto>(adCollection.size());
        for (Ad ad : adCollection) {
            dtoList.add(mapAdToAdDto(ad));
        }
        return dtoList;
    }
}