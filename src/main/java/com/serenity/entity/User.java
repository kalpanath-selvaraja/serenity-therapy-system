package com.serenity.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;



}
