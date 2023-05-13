package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

/**
 * Интерфейс сервисного класса ImageServiceImpl, содержащий набор CRUD операций над объектом Image
 *
 * @see ru.skypro.homework.entity.Image
 * @see ru.skypro.homework.service.impl.ImageServiceImpl
 */
public interface ImageService {

    /**
     * Метод сохраняет картинку в базу данных
     *
     * @param image
     * @return {@link ru.skypro.homework.repository.ImageRepository#saveAndFlush(Object)}
     */
    Image saveImage(MultipartFile image);

    /**
     * Метод обновляет картинку в базе данных
     *
     * @param newImage
     * @param oldImage
     * @return {@link ru.skypro.homework.repository.ImageRepository#saveAndFlush(Object)}
     */
    Image updateImage(MultipartFile newImage, Image oldImage);

    /**
     * Метод достает картинку из базы данных по ее id {@link ru.skypro.homework.repository.ImageRepository#findById(Object)}
     *
     * @param id
     * @throws ru.skypro.homework.exception.ImageNotFoundException
     * @return {@link Image#getImage()}
     */
    byte[] getImageById(String id);
}
