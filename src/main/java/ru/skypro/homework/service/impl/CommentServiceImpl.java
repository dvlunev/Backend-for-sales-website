package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.service.CommentService;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public ResponseWrapperCommentDto getCommentsDto(Long adId) {
        return null;
    }

    @Override
    public Optional<CommentDto> getCommentDtoById(Long adId, Long commentId) {
        return Optional.empty();
    }

    @Override
    public CommentDto createCommentDto(Long adId, CommentDto commentDto) {
        return null;
    }

    @Override
    public boolean removeCommentDto(Long adId, Long commentId) {
        return false;
    }

    @Override
    public CommentDto updateCommentDto(Long adId, Long commentId, CommentDto commentDto) {
        return null;
    }
}
