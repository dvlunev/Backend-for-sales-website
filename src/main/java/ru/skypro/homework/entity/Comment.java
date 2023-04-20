package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "ad_date", length = 20, nullable = false)
    private String createdAt; //дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970

    @Column(length = 1000, nullable = false)
    private String text;
}
