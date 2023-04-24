package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Image;

/**
 * Интерфейс сервисного класса AdServiceImpl, содержащий набор CRUD операций над объектом Ad
 * @see ru.skypro.homework.entity.Ad
 * @see ru.skypro.homework.service.impl.AdServiceImpl
 */
public interface AdService {
    /**
     * Метод ищет и возвращает список всех объявлений
     * @return ResponseWrapperAdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    ResponseWrapperAdsDto getAllAdsDto();

    /**
     * Метод создает объявление
     * @param adDto
     * @param image
     * @return AdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    AdsDto createdAdDto(CreateAdsDto adDto, MultipartFile image);

    /**
     * Метод ищет и возвращает объявление по id
     * @param id
     * @return FullAdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    FullAdsDto getFullAdDto(Long id);

    /**
     * Метод удаляет объявление по id
     * @param id
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    boolean removeAdDto(Long id);

    /**
     * Метод редактирует объявление по id
     * @param id
     * @param adDto
     * @return AdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    AdsDto updateAdDto(Long id, CreateAdsDto adDto);

    /**
     * Метод ищет и возвращает список всех объявлений авторизированного пользователя
     * @return ResponseWrapperAdsDto
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    ResponseWrapperAdsDto getAllUserAdsDto();

    /**
     * Метод обновляет изображение к объявлению по id
     * @param id
     * @param image
     * @see ru.skypro.homework.service.impl.AdServiceImpl
     */
    void updateImageAdDto(Long id, MultipartFile image);
}
