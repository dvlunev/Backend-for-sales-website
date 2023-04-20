package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
@Mapper(componentModel = "spring")
public interface CommentMapper {
//    CommentDto MAPPER = Mappers.getMapper(CommentDto.class);

    CommentDto mapToCommentDto(Comment comment);

    Comment mapToComment(CommentDto commentDto);
}
