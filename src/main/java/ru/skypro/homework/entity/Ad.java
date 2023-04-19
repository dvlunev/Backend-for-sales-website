package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @NotNull
    private int id;

    @ManyToOne
    @JoinColumn (name = "author_id")
    private User author;

    @Column(length = 50)
    private String title;

    @Column(length = 500)
    private String description;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column
    private int price;
}
