package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public ResponseWrapperAdsDto getAllAdsDto() {
        return null;
    }

    @Override
    public AdsDto createdAdDto(CreateAdsDto adDto, Image image) {
        return null;
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
