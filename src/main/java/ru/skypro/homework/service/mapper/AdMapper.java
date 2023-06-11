package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;

import java.util.Collection;

/**
 * AdMapperImpl service class interface for mapping Ad, AdsDto and CreateAdsDto objects
 *
 * @see ru.skypro.homework.entity.Ad
 * @see ru.skypro.homework.dto.AdsDto
 * @see ru.skypro.homework.dto.CreateAdsDto
 * @see ru.skypro.homework.service.mapper.impl.AdMapperImpl
 */
public interface AdMapper {
    /**
     * The method that converts an object of the Ad class into an object of the AdsDto class.
     *
     * @param ad
     * @return AdsDto
     */
    AdsDto mapAdToAdDto(Ad ad);

    /**
     * The method that converts an object of the AdsDto class into an object of the Ad class.
     *
     * @param adsDto
     * @return Ad
     */
    Ad mapAdsDtoToAd(AdsDto adsDto);

    /**
     * The method that converts an object of the Ad class into an object of the FullAdsDto class.
     *
     * @param ad
     * @return FullAdsDto
     */
    FullAdsDto mapAdToFullAdsDTo(Ad ad);

    /**
     * The method that converts an object of the CreateAdsDto class into an object of the Ad class.
     *
     * @param createAdsDto
     * @return Ad
     */
    Ad mapCreatedAdsDtoToAd(CreateAdsDto createAdsDto);

    /**
     * The method that converts a Collection of the Ad classes into a Collection of the AdsDto classes.
     *
     * @param adCollection
     * @return Collection<AdsDto>
     */
    Collection<AdsDto> mapAdListToAdDtoList(Collection<Ad> adCollection);

}

