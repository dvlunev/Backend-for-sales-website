package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс - исключение, описывающий ситуацию, когда изображение не найдено
 *
 * @see ru.skypro.homework.entity.Image
 * @see ru.skypro.homework.service.impl.ImageServiceImpl
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super("Image is not found");
    }
}
