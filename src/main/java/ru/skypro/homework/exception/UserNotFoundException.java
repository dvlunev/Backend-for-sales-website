package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class is an exception that describes the situation when a user is not found
 *
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.UserServiceImpl
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User is not found");
    }
}