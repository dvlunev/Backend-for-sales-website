package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.AdMapper;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final AdMapper adMapper;

    public AdServiceImpl(AdRepository adRepository,
                         UserRepository userRepository,
                         UserService userService, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.adMapper = adMapper;
    }

    @Override
    public ResponseWrapperAdsDto getAllAdsDto() {
        Collection<AdsDto> adsAll = adMapper.mapAdListToAdDtoList(adRepository.findAll());
        return new ResponseWrapperAdsDto(adsAll);
    }

    @Override
    public AdsDto createAds(CreateAdsDto adDto, MultipartFile image) {
        Ad newAd = adMapper.mapCreatedAdsDtoToAd(adDto);
        newAd.setAuthor(userService.findAuthUser().orElseThrow(UserNotFoundException::new));
        adRepository.save(newAd);
        // TO DO сделать сохранение картинки
        return adMapper.mapAdToAdDto(newAd);
    }

    @Override
    public FullAdsDto getFullAdDto(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        return adMapper.mapAdToFullAdsDTo(ad);
    }

    @Override
    public boolean removeAdDto(Integer id) {
        if(adRepository.existsById(id)) {
            adRepository.deleteById(id);
            return true;
        }
        throw new AdsNotFoundException();
    }

    @Override
    public AdsDto updateAdDto(Integer id, CreateAdsDto createAdsDto) {
        Ad ad = adRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        ad.setDescription(createAdsDto.getDescription());
        ad.setPrice(createAdsDto.getPrice());
        ad.setTitle(createAdsDto.getTitle());
        return adMapper.mapAdToAdDto(adRepository.save(ad));
    }

    @Override
    public Collection<AdsDto> getAllUserAdsDto() {
        Collection<Ad> ads = new ArrayList<>();
        // TO DO сделать поиск объявлений по юзеру
        return adMapper.mapAdListToAdDtoList(ads);
    }

    @Override
    public void updateImageAdDto(Integer id, MultipartFile image) {
        // TO DO сделать обновление картинки в объявлении
    }
}
