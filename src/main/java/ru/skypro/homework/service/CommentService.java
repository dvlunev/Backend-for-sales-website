package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserForbiddenException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.mapper.CommentMapper;

/**
 * Интерфейс сервисного класса CommentServiceImpl, содержащий набор CRUD операций над объектом Comment
 *
 * @see ru.skypro.homework.entity.Comment
 * @see ru.skypro.homework.service.impl.CommentServiceImpl
 */
public interface CommentService {
    /**
     * Метод ищет и возвращает список всех комментариев {@link ResponseWrapperCommentDto} к объявлению по id объявления
     *
     * @param adId
     * @return {@link CommentRepository#findAll()}, {@link CommentMapper#mapToCommentDto(Comment)},
     * @see CommentMapper
     */
    ResponseWrapperCommentDto getCommentsDto(Integer adId);

    /**
     * Метод создает комментарий к объявлению по id объявления
     *
     * @param adId
     * @param commentDto
     * @return {@link CommentRepository#save(Object)}, {@link CommentMapper#mapToCommentDto(Comment)}
     * @throws AdsNotFoundException  если объявление по указанному id не найдено
     * @throws UserNotFoundException если пользователь не найден
     * @see CommentMapper
     */
    CommentDto createCommentDto(Integer adId, CommentDto commentDto);

    /**
     * Метод удаляет комментарий к объявлению по id объявления
     *
     * @param adId
     * @param commentId
     * @return {@link CommentRepository#delete(Object)}
     * @throws UserForbiddenException если нет прав на удаление комментария
     */
    boolean removeCommentDto(Integer adId, Integer commentId);

    /**
     * Метод редактирует комментарий к объявлению по id
     *
     * @param adId
     * @param commentId
     * @param commentDto
     * @return {@link CommentRepository#save(Object)}, {@link CommentMapper#mapToCommentDto(Comment)}
     * @throws CommentNotFoundException если комментарий не найден
     * @throws UserForbiddenException   если нет прав на обновление комментария
     * @see CommentMapper
     */
    CommentDto updateCommentDto(Integer adId, Integer commentId, CommentDto commentDto);

    /**
     * Метод проверяет наличие доступа к комментарию по id
     *
     * @param id
     * @throws CommentNotFoundException если комментарий не найден
     * @see UserService
     */
    boolean checkAccess(Integer id);
}
