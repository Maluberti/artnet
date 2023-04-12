package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;
    private Boolean isArtist;
    private Boolean isAdmirer;

    public Users(String name, String email, String password, Boolean isArtist, Boolean isAdmirer) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isArtist = isArtist;
        this.isAdmirer = isAdmirer;
    }
}