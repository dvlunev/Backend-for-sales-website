package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.UserForbiddenException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.AdMapper;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The service class containing the implementation of the interface {@link AdService}
 *
 * @see Ad
 * @see AdRepository
 */
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdMapper adMapper;

    /**
     * The method searches and returns a list of all ads
     *
     * @return ResponseWrapperAdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    @Override
    public ResponseWrapperAdsDto getAllAdsDto() {
        Collection<AdsDto> adsAll = adMapper.mapAdListToAdDtoList(adRepository.findAll());
        return new ResponseWrapperAdsDto(adsAll);
    }

    /**
     * The method creates an ad
     *
     * @param adDto
     * @param image
     * @return AdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    @Override
    public AdsDto createAds(CreateAdsDto adDto, MultipartFile image) {
        Ad newAd = adMapper.mapCreatedAdsDtoToAd(adDto);
        newAd.setAuthor(userService.findAuthUser().orElseThrow(UserNotFoundException::new));
        Image newImage = imageService.saveImage(image);
        newAd.setImage(newImage);
        adRepository.save(newAd);
        return adMapper.mapAdToAdDto(newAd);
    }

    /**
     * The method searches for and returns the ad by id
     *
     * @param id
     * @return FullAdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    @Override
    public FullAdsDto getFullAdDto(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        return adMapper.mapAdToFullAdsDTo(ad);
    }

    /**
     * The method removes the ad by id
     *
     * @param id
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    @Override
    public boolean removeAdDto(Integer id) {
        if (checkAccess(id)) {
            adRepository.deleteById(id);
            return true;
        }
        throw new UserForbiddenException();
    }

    /**
     * The method edits the ad by id
     *
     * @param id
     * @param createAdsDto
     * @return AdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    @Override
    public AdsDto updateAdDto(Integer id, CreateAdsDto createAdsDto) {
        Ad ad = adRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        if (checkAccess(id)) {
            ad.setDescription(createAdsDto.getDescription());
            ad.setPrice(createAdsDto.getPrice());
            ad.setTitle(createAdsDto.getTitle());
            return adMapper.mapAdToAdDto(adRepository.save(ad));
        }
        throw new UserForbiddenException();
    }

    /**
     * The method searches and returns a list of all the authorized user's ads
     *
     * @return ResponseWrapperAdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    @Override
    public ResponseWrapperAdsDto getAllUserAdsDto() {
        User user = userService.findAuthUser().orElseThrow(UserNotFoundException::new);
        Collection<Ad> allAds = adRepository.findAll();
        Collection<Ad> userAds = allAds.stream().filter(x -> x.getAuthor().equals(user)).collect(Collectors.toList());
        return new ResponseWrapperAdsDto(adMapper.mapAdListToAdDtoList(userAds));
    }

    /**
     * The method updates the ad`s image by id
     *
     * @param id
     * @param image
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    @Override
    public void updateImageAdDto(Integer id, MultipartFile image) {
        Ad ad = adRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        Image updatedImage = imageService.updateImage(image, ad.getImage());
        ad.setImage(updatedImage);
        adRepository.save(ad);
    }

    /**
     * The method checks for access to the ad by id
     *
     * @param id
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    @Override
    public boolean checkAccess(Integer id) {
        Role role = Role.ADMIN;
        Ad ad = adRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        Optional<User> user = userService.findAuthUser();
        User notOptionalUser = user.get();
        String currentPrincipalName = notOptionalUser.getUsername();
        return ad.getAuthor().getUsername().equals(currentPrincipalName)
                || notOptionalUser.getAuthorities().contains(role);
    }
}
