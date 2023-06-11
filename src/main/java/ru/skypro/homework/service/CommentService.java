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
 * Service class interface CommentServiceImpl containing a set of CRUD operations on the Comment object
 *
 * @see ru.skypro.homework.entity.Comment
 * @see ru.skypro.homework.service.impl.CommentServiceImpl
 */
public interface CommentService {
    /**
     * The method searches and returns a list of all ad`s comments by ad id {@link ResponseWrapperCommentDto}
     *
     * @param adId
     * @return {@link CommentRepository#findAll()}, {@link CommentMapper#mapToCommentDto(Comment)},
     * @see CommentMapper
     */
    ResponseWrapperCommentDto getCommentsDto(Integer adId);

    /**
     * The method creates a comment to the ad by ad id
     *
     * @param adId
     * @param commentDto
     * @return {@link CommentRepository#save(Object)}, {@link CommentMapper#mapToCommentDto(Comment)}
     * @throws AdsNotFoundException  if the ad by id is not found
     * @throws UserNotFoundException if the user is not found
     * @see CommentMapper
     */
    CommentDto createCommentDto(Integer adId, CommentDto commentDto);

    /**
     * The method removes the ad`s comment by ad`s id
     *
     * @param adId
     * @param commentId
     * @return {@link CommentRepository#delete(Object)}
     * @throws UserForbiddenException if there are no rights to remove the comment
     */
    boolean removeCommentDto(Integer adId, Integer commentId);

    /**
     * The method edits the ad`s comment by id
     *
     * @param adId
     * @param commentId
     * @param commentDto
     * @return {@link CommentRepository#save(Object)}, {@link CommentMapper#mapToCommentDto(Comment)}
     * @throws CommentNotFoundException if the comment is not found
     * @throws UserForbiddenException   if there are no rights of comment updating
     * @see CommentMapper
     */
    CommentDto updateCommentDto(Integer adId, Integer commentId, CommentDto commentDto);

    /**
     * The method checks for access to the comment by id
     *
     * @param id
     * @throws CommentNotFoundException if the comment is not found
     * @see UserService
     */
    boolean checkAccess(Integer id);
}
