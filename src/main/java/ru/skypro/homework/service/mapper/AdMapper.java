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
     * Метод для маппинга объекта типа Ad на объект типа AdDto.
     *
     * @param ad
     * @return AdsDto
     */
    AdsDto mapAdToAdDto(Ad ad);

    /**
     * Метод для маппинга объекта типа AdsDto на объект типа Ad.
     *
     * @param adsDto
     * @return Ad
     */
    Ad mapAdsDtoToAd(AdsDto adsDto);

    /**
     * Метод для маппинга объекта типа Ad на объект типа FullAdsDto.
     *
     * @param ad
     * @return FullAdsDto
     */
    FullAdsDto mapAdToFullAdsDTo(Ad ad);

    /**
     * Метод для маппинга объекта типа CreateAdsDto на объект типа Ad.
     *
     * @param createAdsDto
     * @return Ad
     */
    Ad mapCreatedAdsDtoToAd(CreateAdsDto createAdsDto);

    /**
     * Метод для маппинга объекта типа Collection<Ad> на объект типа Collection<AdsDto>.
     *
     * @param adCollection
     * @return Collection<AdsDto>
     */
    Collection<AdsDto> mapAdListToAdDtoList(Collection<Ad> adCollection);

}

