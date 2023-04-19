package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "site_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @NotNull
    private int id;

    @Column(length = 50)
    private String email;

    @Column(name = "first_name", length = 25)
    private String firstName;

    @Column(name = "last_name", length = 25)
    private String lastName;

    @Column(length = 15)
    private String phone;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(length = 50)
    private String password;

    @Column(length = 50, name = "user_name")
    private String username;

   @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
