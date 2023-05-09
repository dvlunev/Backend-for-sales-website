package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

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

    @Override
    public byte[] getImagePathById(String id) {
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        return image.getImage();
    }
}
