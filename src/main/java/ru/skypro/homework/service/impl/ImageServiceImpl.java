package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.UUID;

/**
 * The service class containing the implementation of the interface {@link ImageService}
 *
 * @see Image
 * @see ImageRepository
 */
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    /**
     * The method saves an image to the database
     *
     * @param image
     * @return {@link ru.skypro.homework.repository.ImageRepository#saveAndFlush(Object)}
     */
    @Override
    public Image saveImage(MultipartFile image) {
        Image newImage = new Image();
        try {
            byte[] bytes = image.getBytes();
            newImage.setImage(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newImage.setId(UUID.randomUUID().toString());
        return imageRepository.saveAndFlush(newImage);
    }

    /**
     * The method updates the image in the database
     *
     * @param newImage
     * @param oldImage
     * @return {@link ru.skypro.homework.repository.ImageRepository#saveAndFlush(Object)}
     */
    @Override
    public Image updateImage(MultipartFile newImage, Image oldImage) {
        try {
            byte[] bytes = newImage.getBytes();
            oldImage.setImage(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageRepository.saveAndFlush(oldImage);
    }

    /**
     * The method gets an image from database by its id {@link ru.skypro.homework.repository.ImageRepository#findById(Object)}
     *
     * @param id
     * @throws ru.skypro.homework.exception.ImageNotFoundException
     * @return {@link Image#getImage()}
     */
    @Override
    public byte[] getImageById(String id) {
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        return image.getImage();
    }
}
