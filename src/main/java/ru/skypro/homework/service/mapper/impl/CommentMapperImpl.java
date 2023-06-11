package ru.skypro.homework.service.mapper.impl;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.service.mapper.CommentMapper;

import java.time.ZoneId;
import java.util.Optional;

/**
 * Service-mapper class containing the implementation of the interface {@link CommentMapper}
 */
@Component
public class CommentMapperImpl implements CommentMapper {

    /**
     * The method that converts an object of the Comment class into an object of the CommentDto class.
     *
     * @param comment
     * @return CommentDto
     */
    public CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comment.getId());
        commentDto.setAuthor(comment.getAuthor().getId());
        Optional.ofNullable(comment.getAuthor().getImage()).ifPresent(image -> commentDto.setAuthorImage(
                "/users/" + comment.getAuthor().getImage().getId() + "/image"));
        commentDto.setAuthorFirstName(comment.getAuthor().getFirstName());
        commentDto.setCreatedAt(comment.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        commentDto.setText(comment.getText());
        return commentDto;
    }

    /**
     * The method that converts an object of the CommentDto class into an object of the Comment class.
     *
     * @param commentDto
     * @return Comment
     */
    public Comment mapToComment(CommentDto commentDto) {
        Comment mappedComment = new Comment();
        mappedComment.setId(commentDto.getPk());
        mappedComment.setText(commentDto.getText());
        return mappedComment;
    }
}
