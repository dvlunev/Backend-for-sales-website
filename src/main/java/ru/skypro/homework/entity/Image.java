package ru.skypro.homework.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

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

    @Column(name = "image_path", length = 200, nullable = false)
    private String imagePath;
    //путь к картинке в файловой системе


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        if (id != 0)
            return id == image.id;
        else
        return Objects.equals(imagePath, image.imagePath);
    }

    @Override
    public int hashCode() {
        if (id != 0)
            return Objects.hash(id);
        else
        return Objects.hash(imagePath);
    }
}
