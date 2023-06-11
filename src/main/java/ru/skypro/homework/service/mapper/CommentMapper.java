package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;

/**
 * CommentMapperImpl service class interface for mapping Comment and CommentDto objects
 *
 * @see ru.skypro.homework.entity.Comment
 * @see ru.skypro.homework.dto.CommentDto
 * @see ru.skypro.homework.service.mapper.impl.CommentMapperImpl
 */
public interface CommentMapper {
    /**
     * The method that converts an object of the Comment class into an object of the CommentDto class.
     *
     * @param comment
     * @return CommentDto
     */
    CommentDto mapToCommentDto(Comment comment);

    /**
     * The method that converts an object of the CommentDto class into an object of the Comment class.
     *
     * @param commentDto
     * @return Comment
     */
    Comment mapToComment(CommentDto commentDto);
}
