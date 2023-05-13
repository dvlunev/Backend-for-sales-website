package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;

import java.util.Collection;

/**
 * Интерфейс сервисного класса-маппера AdMapperImpl, для маппинга объектов Ad, AdsDto и CreateAdsDto
 *
 * @see ru.skypro.homework.entity.Ad
 * @see ru.skypro.homework.dto.AdsDto
 * @see ru.skypro.homework.dto.CreateAdsDto
 * @see ru.skypro.homework.service.mapper.impl.AdMapperImpl
 */
public interface AdMapper {
    /**
     * Метод, преобразующий объект класса Ad в объект класса AdsDto.
     *
     * @param ad
     * @return AdsDto
     */
    AdsDto mapAdToAdDto(Ad ad);

    /**
     * Метод, преобразующий объект класса AdsDto в объект класса Ad.
     *
     * @param adsDto
     * @return Ad
     */
    Ad mapAdsDtoToAd(AdsDto adsDto);

    /**
     * Метод, преобразующий объект класса Ad в объект класса FullAdsDto.
     *
     * @param ad
     * @return FullAdsDto
     */
    FullAdsDto mapAdToFullAdsDTo(Ad ad);

    /**
     * Метод, преобразующий объект класса CreateAdsDto в объект класса Ad.
     *
     * @param createAdsDto
     * @return Ad
     */
    Ad mapCreatedAdsDtoToAd(CreateAdsDto createAdsDto);

    /**
     * Метод, преобразующий объект класса Collection<Ad> в объект класса Collection<AdsDto>.
     *
     * @param adCollection
     * @return Collection<AdsDto>
     */
    Collection<AdsDto> mapAdListToAdDtoList(Collection<Ad> adCollection);

}

