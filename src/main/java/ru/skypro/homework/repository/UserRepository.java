package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * Интерфейс, содержащий методы для работы с базой данных пользователей
 * @see ru.skypro.homework.entity.User
 * @see ru.skypro.homework.service.UserService
 * @see ru.skypro.homework.service.impl.UserServiceImpl
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
