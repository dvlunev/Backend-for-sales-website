package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.AdMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final ImageRepository imageRepository;
    private final UserService userService;
    private final AdMapper adMapper;

    public AdServiceImpl(AdRepository adRepository,
                         ImageRepository imageRepository,
                         UserService userService, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.imageRepository = imageRepository;
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
        Image newImage = new Image();
        try {
            byte[] bytes = image.getBytes();
            newImage.setImagePath(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newImage.setId(UUID.randomUUID().toString());
        Image savedImage = imageRepository.saveAndFlush(newImage);
        newAd.setImage(savedImage);
        adRepository.save(newAd);
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
    public ResponseWrapperAdsDto getAllUserAdsDto() {
        User user = userService.findAuthUser().orElseThrow(UserNotFoundException::new);
        Collection<Ad> allAds = adRepository.findAll();
        Collection<Ad> userAds = allAds.stream().filter(x -> x.getAuthor().equals(user)).collect(Collectors.toList());
        return new ResponseWrapperAdsDto(adMapper.mapAdListToAdDtoList(userAds));
    }

    @Override
    public void updateImageAdDto(Integer id, MultipartFile image) {
        Ad ad = adRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        Image oldImage = ad.getImage();
        try {
            byte[] bytes = image.getBytes();
            oldImage.setImagePath(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image savedImage = imageRepository.saveAndFlush(oldImage);
        ad.setImage(savedImage);
        adRepository.save(ad);
    }
}
