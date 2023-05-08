package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс - исключение, описывающий ситуацию, когда комментарий не найден
 * @see ru.skypro.homework.entity.Comment
 * @see ru.skypro.homework.service.impl.CommentServiceImpl
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {
        super("Comment is not found");
    }
}
