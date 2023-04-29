package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.mapper.CommentMapper;

import java.util.ArrayList;
import java.util.List;

/**
 *Класс - сервис, содержащий реализацию интерфейса CommentService
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

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper,
                              AdRepository adRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
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
     * @throws NullPointerException если объявление по указанному id не найдено
     * @see CommentService
     */
    @Override
    public CommentDto createCommentDto(Integer adId, CommentDto commentDto) {
        Ad ad = adRepository.findById(adId).orElseThrow(NullPointerException::new);
        /*int author = commentDto.getAuthor();
        User user = userRepository.findById(author).orElseThrow(NullPointerException::new);*/
        Comment comment = commentMapper.mapToComment(commentDto);
        comment.setAd(ad);
        commentRepository.save(comment);
        return  commentMapper.mapToCommentDto(comment);
    }

    /**
     * Метод удаляет комментарий к объявлению по id объявления
     * @param adId
     * @param commentId
     * @throws NullPointerException если объявление по указанному id не найдено
     * @throws CommentNotFoundException если комментарий с указанным id объявления не найден
     * @see CommentService
     */
    @Override
    public boolean removeCommentDto(Integer adId, Integer commentId) {
        Ad ad = adRepository.findById(adId).orElseThrow(NullPointerException::new);
        Comment commentBD = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        List<Comment> comments = ad.getComments();
        for (Comment comment : comments) {
            if (commentBD.equals(comment)) {
                comments.remove(comment);
                commentRepository.delete(commentBD);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод редактирует комментарий к объявлению по id
     * @param adId
     * @param commentId
     * @param commentDto
     * @return {@link CommentRepository#save(Object)}
     * @throws NullPointerException если объявление по указанному id не найдено
     * @throws CommentNotFoundException если комментарий с указанным id объявления не найден
     * @see CommentService
     */
    @Override
    public CommentDto updateCommentDto(Integer adId, Integer commentId, CommentDto commentDto) {
        Ad ad = adRepository.findById(adId).orElseThrow(NullPointerException::new);
        Comment commentBD = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        List<Comment> comments = ad.getComments();
        for (Comment comment : comments) {
            if (commentBD.equals(comment)) {
                comment.setText(commentDto.getText());
                commentRepository.save(comment);
                return commentMapper.mapToCommentDto(comment);
            }
        }
        commentBD.setText(commentDto.getText());//?
        commentRepository.save(commentBD);
        return commentMapper.mapToCommentDto(commentBD);
        //return createCommentDto(adId, commentDto);
    }
}
