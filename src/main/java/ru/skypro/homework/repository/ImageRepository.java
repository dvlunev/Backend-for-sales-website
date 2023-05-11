package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Image;

/**
 * Интерфейс, содержащий методы для работы с базой данных изображений
 *
 * @see Image
 * @see ru.skypro.homework.service.ImageService
 * @see ru.skypro.homework.service.impl.ImageServiceImpl
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
