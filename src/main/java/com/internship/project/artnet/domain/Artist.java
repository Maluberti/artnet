package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Artist extends Users {


    private Integer phone;
    private String biographic;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private Set<Exposition> expositions = new HashSet<>();

    @ManyToMany(mappedBy = "artists") // as instrucoes da tabela de uniao vao estar atreladas a categories, campo de recipe
    private Set<Admirer> admirers;

    @ManyToMany  // manyto many gera uma terceira tabela e o jointable fala como montar
    @JoinTable(name = "Artists_has_Style",  //nome
            joinColumns = @JoinColumn(name = "artist_id"),  //deste "lado" temos os id de receita
            inverseJoinColumns = @JoinColumn(name = "style_id"))  // do outro lado os id da categoria
    private Set<Style> styles = new HashSet<>();

    public Artist(String name, String email, String password, Boolean isArtist, Boolean isAdmirer, Integer phone, String biographic) {
        super(name, email, password, isArtist, isAdmirer);
        this.phone = phone;
        this.biographic = biographic;
    }

}

