package com.begenerous.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(
        name = "app_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique", columnNames = "email"
                )
        }
)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "user_id"
    )
    private Long userId;

    @Column(
            name = "email",
            unique = true
    )
    private String email;

    @Column(
            name = "password"
    )
    private String password;

    @Column(
            name = "full_name"
    )
    private String fullName;

    @Column(
            name = "avatar_url"
    )
    private String avatarURL;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Charity> charities = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<CreditCard> creditCard = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Comment> comment = new ArrayList<>();

    public User(Long userId, String email, String password, String fullName, String avatarURL) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatarURL = avatarURL;
    }
}
