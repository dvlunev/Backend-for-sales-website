package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

//@Mapper(componentModel = "spring",uses = User.class)
public interface AutoCommentMapper {
    @Mapping(target = "author",source = "author", qualifiedByName = "userToInt")
    CommentDto mapToCommentDto(Comment comment);
    @Named("userToInt")
    Integer userToInt(User user);

    @Mapping(target = "author",source = "authorFirstName", qualifiedByName = "StringToUser")
    Comment mapToComment(CommentDto commentDto);
    @Named("StringToUser")
    User setUserByFirstName(String firstName);
}
