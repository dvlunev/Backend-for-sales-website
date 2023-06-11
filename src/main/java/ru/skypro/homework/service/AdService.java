package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;


/**
 * Service class interface AdServiceImpl containing a set of CRUD operations on the Ad object
 *
 * @see ru.skypro.homework.entity.Ad
 * @see ru.skypro.homework.service.impl.AdServiceImpl
 */
public interface AdService {
    /**
     * The method searches and returns the list of all ads
     *
     * @return ResponseWrapperAdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    ResponseWrapperAdsDto getAllAdsDto();

    /**
     * The method creates an ad
     *
     * @param adDto
     * @param image
     * @return AdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    AdsDto createAds(CreateAdsDto adDto, MultipartFile image);

    /**
     * The method searches and returns the ad by id
     *
     * @param id
     * @return FullAdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    FullAdsDto getFullAdDto(Integer id);

    /**
     * The method removes the ad by id
     *
     * @param id
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    boolean removeAdDto(Integer id);

    /**
     * The method edits the ad by id
     *
     * @param id
     * @param createAdsDto
     * @return AdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    AdsDto updateAdDto(Integer id, CreateAdsDto createAdsDto);

    /**
     * The method searches and returns the list of all the authorised user`s ads
     *
     * @return ResponseWrapperAdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    ResponseWrapperAdsDto getAllUserAdsDto();

    /**
     * The method updates the ad`s image by id
     *
     * @param id
     * @param image
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    void updateImageAdDto(Integer id, MultipartFile image);

    /**
     * The method checks for access to the ad by id
     *
     * @param id
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    boolean checkAccess(Integer id);
}
