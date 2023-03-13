package com.estagio.artnetproject.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Artist extends Users {


    private int phone;
    private String biographic;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private Set<Exposition> expositions = new HashSet<>();

    public Artist() {
        super();
    }

    public Artist(int phone, String biographic) {
        this.phone = phone;
        this.biographic = biographic;
    }

    public Artist(Long id, String name, String email, String password, Boolean isArtist, Boolean isAdmirer, int phone, String biographic) {
        super(id, name, email, password, isArtist, isAdmirer);
        this.phone = phone;
        this.biographic = biographic;
    }


}
