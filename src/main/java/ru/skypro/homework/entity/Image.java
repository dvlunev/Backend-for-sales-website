package ru.skypro.homework.entity;

import lombok.*;
import javax.persistence.*;

/**
 * Класс, описывающий изображения
 Содержит аватары для пользователя {@link User} и фото для объявлений {@link Ad}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(name = "image_link", length = 200, nullable = false)
    private String imageLink;
    //путь к картинке в файловой системе
}
