package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Comment;

/**
 * Interface containing methods for working with the comment database
 *
 * @see Comment
 * @see ru.skypro.homework.service.CommentService
 * @see ru.skypro.homework.service.impl.CommentServiceImpl
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
