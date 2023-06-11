package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserForbiddenException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.CommentMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The service class containing the implementation of the interface {@link CommentService}
 *
 * @see Comment
 * @see CommentRepository
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdRepository adRepository;
    private final UserService userService;

    /**
     * The method searches and returns a list of all ad`s comments by ad id {@link ResponseWrapperCommentDto}
     *
     * @param adId
     * @return {@link CommentRepository#findAll()}, {@link CommentMapper#mapToCommentDto(Comment)},
     * @see CommentMapper
     */
    @Override
    public ResponseWrapperCommentDto getCommentsDto(Integer adId) {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            if (adId.equals(comment.getAd().getId())) {
                commentDtoList.add(commentMapper.mapToCommentDto(comment));
            }
        }
        return new ResponseWrapperCommentDto(commentDtoList);
    }

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
    @Override
    public CommentDto createCommentDto(Integer adId, CommentDto commentDto) {
        Ad ad = adRepository.findById(adId).orElseThrow(AdsNotFoundException::new);
        Comment comment = commentMapper.mapToComment(commentDto);
        comment.setAuthor(userService.findAuthUser().orElseThrow(UserNotFoundException::new));
        comment.setAd(ad);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return commentMapper.mapToCommentDto(comment);
    }

    /**
     * The method removes the ad`s comment by ad`s id
     *
     * @param adId
     * @param commentId
     * @return {@link CommentRepository#delete(Object)}
     * @throws UserForbiddenException if there are no rights to remove the comment
     */
    @Override
    public boolean removeCommentDto(Integer adId, Integer commentId) {
        if (checkAccess(commentId)) {
            commentRepository.deleteById(commentId);
            return true;
        }
        throw new UserForbiddenException();
    }

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
    @Override
    public CommentDto updateCommentDto(Integer adId, Integer commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if (checkAccess(commentId)) {
            comment.setText(commentDto.getText());
            commentRepository.save(comment);
            return commentMapper.mapToCommentDto(comment);
        }
        throw new UserForbiddenException();
    }

    /**
     * The method checks for access to the comment by id
     *
     * @param id
     * @throws CommentNotFoundException if the comment is not found
     * @see UserService
     */
    @Override
    public boolean checkAccess(Integer id) {
        Role role = Role.ADMIN;
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        Optional<User> user = userService.findAuthUser();
        User notOptionalUser = user.get();
        String currentPrincipalName = notOptionalUser.getUsername();
        return comment.getAuthor().getUsername().equals(currentPrincipalName)
                || notOptionalUser.getAuthorities().contains(role);
    }
}
