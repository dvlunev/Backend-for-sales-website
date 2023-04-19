package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "site_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(length = 50, nullable = false)
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

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 50, name = "user_name", nullable = false)
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ad> ads = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public User(int id, String email, String firstName, String lastName, String phone, Image image, String password, String username, Role role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
       this.image = image;
        this.password = password;
        this.username = username;
        this.role = role;
    }
}
