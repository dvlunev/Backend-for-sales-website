package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;

import java.util.Optional;

/**
 * Интерфейс сервисного класса CommentServiceImpl, содержащий набор CRUD операций над объектом Comment
 * @see ru.skypro.homework.entity.Comment
 * @see ru.skypro.homework.service.impl.CommentServiceImpl
 */
public interface CommentService {
    /**
     * Метод ищет и возвращает список всех комментариев к объявлению по id объявления
     * @param adId
     * @return ResponseWrapperCommentDto
     * @see ru.skypro.homework.service.impl.CommentServiceImpl
     */
    ResponseWrapperCommentDto getCommentsDto(Long adId);

    /**
     * Метод ищет и возвращает комментарий к объявлению по id
     * @param adId
     * @param commentId
     * @return ResponseWrapperCommentDto
     * @see ru.skypro.homework.service.impl.CommentServiceImpl
     */
    Optional<CommentDto> getCommentDtoById(Long adId, Long commentId);

    /**
     * Метод создает комментарий к объявлению по id объявления
     * @param adId
     * @param commentDto
     * @return CommentDto
     * @see ru.skypro.homework.service.impl.CommentServiceImpl
     */
    CommentDto createCommentDto(Long adId, CommentDto commentDto);

    /**
     * Метод удаляет комментарий к объявлению по id объявления
     * @param adId
     * @param commentId
     * @see ru.skypro.homework.service.impl.CommentServiceImpl
     */
    boolean removeCommentDto(Long adId, Long commentId);

    /**
     * Метод редактирует комментарий к объявлению по id
     * @param adId
     * @param commentId
     * @return CommentDto
     * @see ru.skypro.homework.service.impl.CommentServiceImpl
     */
    CommentDto updateCommentDto(Long adId, Long commentId, CommentDto commentDto);
}
