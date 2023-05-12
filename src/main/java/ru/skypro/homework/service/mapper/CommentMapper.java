package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;

/**
 * Интерфейс сервисного класса-маппера CommentMapperImpl, для маппинга объектов CommentDto и Comment
 *
 * @see ru.skypro.homework.entity.Comment
 * @see ru.skypro.homework.dto.CommentDto
 * @see ru.skypro.homework.service.mapper.impl.CommentMapperImpl
 */
public interface CommentMapper {
    /**
     * Метод для маппинга объекта типа Comment на объект типа CommentDto.
     *
     * @param comment
     * @return CommentDto
     */
    CommentDto mapToCommentDto(Comment comment);

    /**
     * Метод для маппинга объекта типа CommentDto на объект типа Comment.
     *
     * @param commentDto
     * @return Comment
     */
    Comment mapToComment(CommentDto commentDto);
}
