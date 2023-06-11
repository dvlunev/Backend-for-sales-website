package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

/**
 * Service class interface ImageServiceImpl containing a set of CRUD operations on the Image object
 *
 * @see ru.skypro.homework.entity.Image
 * @see ru.skypro.homework.service.impl.ImageServiceImpl
 */
public interface ImageService {

    /**
     * The method saves the image to the database
     *
     * @param image
     * @return {@link ru.skypro.homework.repository.ImageRepository#saveAndFlush(Object)}
     */
    Image saveImage(MultipartFile image);

    /**
     * The method updates the image in the database
     *
     * @param newImage
     * @param oldImage
     * @return {@link ru.skypro.homework.repository.ImageRepository#saveAndFlush(Object)}
     */
    Image updateImage(MultipartFile newImage, Image oldImage);

    /**
     * The method gets the image from the database by id {@link ru.skypro.homework.repository.ImageRepository#findById(Object)}
     *
     * @param id
     * @throws ru.skypro.homework.exception.ImageNotFoundException
     * @return {@link Image#getImage()}
     */
    byte[] getImageById(String id);
}
