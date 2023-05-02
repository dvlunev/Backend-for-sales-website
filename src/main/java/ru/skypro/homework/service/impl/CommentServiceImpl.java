package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.CommentMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс - сервис, содержащий реализацию интерфейса CommentService
 * @see ru.skypro.homework.entity.Comment
 * @see ru.skypro.homework.service.CommentService
 * @see ru.skypro.homework.repository.CommentRepository
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper,
                              AdRepository adRepository, UserRepository userRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * Метод ищет и возвращает список всех комментариев к объявлению по id объявления
     * @param adId
     * @return {@link CommentRepository#findAll()}
     * @see CommentService
     */
    @Override
    public ResponseWrapperCommentDto getCommentsDto(Integer adId) {
        /*Ad ad = adRepository.findById(adId).orElseThrow(CommentNotFoundException::new);
        List<Comment> comments = ad.getComments();*/
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            if (adId.equals(comment.getAd().getId())) {
                commentDtoList.add(commentMapper.mapToCommentDto(comment));
            }
        }
        return new ResponseWrapperCommentDto(commentDtoList);
    }

    /*@Override
    public CommentDto getCommentDtoById(Integer commentId) {
        Comment comment = commentRepository.findById(Long.valueOf(commentId)).orElseThrow(CommentNotFoundException::new);
        return commentMapper.mapToCommentDto(comment);
    }*/

    /**
     * Метод создает комментарий к объявлению по id объявления
     * @param adId
     * @param commentDto
     * @return {@link CommentRepository#save(Object)}
     * @throws AdsNotFoundException если объявление по указанному id не найдено
     * @see CommentService
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
     * Метод удаляет комментарий к объявлению по id объявления
     * @param adId
     * @param commentId
     * @throws CommentNotFoundException если комментарий с указанным id объявления не найден
     * @see CommentService
     */
    @Override
    public boolean removeCommentDto(Integer adId, Integer commentId) {
        if(commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return true;
        }
        throw new CommentNotFoundException();
    }

    /**
     * Метод редактирует комментарий к объявлению по id
     * @param adId
     * @param commentId
     * @param commentDto
     * @return {@link CommentRepository#save(Object)}
     * @throws CommentNotFoundException если комментарий с указанным id объявления не найден
     * @see CommentService
     */
    @Override
    public CommentDto updateCommentDto(Integer adId, Integer commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.setText(commentDto.getText());
        return commentMapper.mapToCommentDto(commentRepository.save(comment));
    }
}
