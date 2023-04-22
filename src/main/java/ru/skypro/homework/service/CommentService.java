package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;

public interface CommentService {
    ResponseWrapperCommentDto getCommentsDto(Long adId);
    CommentDto createCommentDto(Long adId, CommentDto commentDto);
    boolean removeCommentDto(Long adId, Long commentId);
    CommentDto updateCommentDto(Long adId, Long commentId, CommentDto commentDto);
}
