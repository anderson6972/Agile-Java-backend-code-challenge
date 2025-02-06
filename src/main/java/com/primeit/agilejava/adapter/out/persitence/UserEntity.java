package com.primeit.agilejava.adapter.out.persitence;

import com.primeit.agilejava.domain.common.CustomGender;
import jakarta.persistence.*;
import lombok.*;

/**
 * Database mapping class
 */
@Entity
@Getter @Setter @ToString
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "username")
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private CustomGender gender;

    @Column(name = "photo")
    private String photo;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;
}
