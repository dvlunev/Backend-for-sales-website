package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *The class is an exception that describes the situation,
 * when the user is authenticated but not authorized to access the resource
 *
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.UserServiceImpl
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserForbiddenException extends RuntimeException {
    public UserForbiddenException() {
        super("User is authenticated but does not have permission to access the resource");
    }
}
