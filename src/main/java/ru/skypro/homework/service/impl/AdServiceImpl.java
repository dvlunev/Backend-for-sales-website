package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.mapper.AdMapper;

import java.util.Collection;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final CommentRepository commentRepository;

    public AdServiceImpl(AdRepository adRepository,
                         UserRepository userRepository,
                         AdMapper adMapper,
                         CommentRepository commentRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public ResponseWrapperAdsDto getAllAdsDto() {
        Collection<AdsDto> adsAll = adMapper.entityToDtoList(adRepository.findAll());
        return new ResponseWrapperAdsDto(adsAll);
    }

    @Override
    public AdsDto createdAdDto(CreateAdsDto adDto, MultipartFile image) {
        Ad newAd = adMapper.createdAdsDtoToAd(adDto);
        adRepository.save(newAd);
        // TO DO сделать сохранение картинки
        return adMapper.mapToAdDto(newAd);
    }

    @Override
    public FullAdsDto getFullAdDto(Long id) {
        return null;
    }

    @Override
    public boolean removeAdDto(Long id) {
        return false;
    }

    @Override
    public AdsDto updateAdDto(Long id, CreateAdsDto adDto) {
        return null;
    }

    @Override
    public ResponseWrapperAdsDto getAllUserAdsDto() {
        return null;
    }

    @Override
    public void updateImageAdDto(Long id, MultipartFile image) {

    }
}
