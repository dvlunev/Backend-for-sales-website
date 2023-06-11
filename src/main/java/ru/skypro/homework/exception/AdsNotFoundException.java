package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class is an exception that describes the situation when an ad is not found
 *
 * @see ru.skypro.homework.entity.Ad
 * @see ru.skypro.homework.service.impl.AdServiceImpl
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AdsNotFoundException extends RuntimeException {
    public AdsNotFoundException() {
        super("Ad is not found");
    }
}
