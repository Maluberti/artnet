package com.estagio.artnetproject.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
    @Id
    private Long id;

    private String name;
    private String email;
    private String password;
    private Boolean isArtist;
    private Boolean isAdmirer;

    public Users() {
    }

    public Users(Long id, String name, String email, String password, Boolean isArtist, Boolean isAdmirer) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isArtist = isArtist;
        this.isAdmirer = isAdmirer;
    }




}
