package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс - исключение, описывающий ситуацию,
 * когда пользователь не прошел аутентификацию и поэтому не имеет права доступа к ресурсу
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.impl.UserServiceImpl
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends RuntimeException {
    public UserUnauthorizedException() {
        super("User is not authenticated and therefore not authorized to access the resource");
    }
}
