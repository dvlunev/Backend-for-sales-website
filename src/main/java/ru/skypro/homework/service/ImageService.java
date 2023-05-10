package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

/**
 * Интерфейс сервисного класса ImageServiceImpl, содержащий набор CRUD операций над объектом Image
 * @see ru.skypro.homework.entity.Image
 * @see ru.skypro.homework.service.impl.ImageServiceImpl
 */
public interface ImageService {
    Image saveImage(MultipartFile image);
    Image updateImage(MultipartFile newImage, Image oldImage);
    byte[] getImagePathById(String id);
}
