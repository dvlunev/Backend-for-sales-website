package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * Interface containing methods for working with the user database
 *
 * @see User
 * @see ru.skypro.homework.service.UserService
 * @see ru.skypro.homework.service.impl.UserServiceImpl
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * The method searches and returns the user by his email
     *
     * @param email
     * @return User
     * @see User
     */
    Optional<User> findByEmail(String email);
}
