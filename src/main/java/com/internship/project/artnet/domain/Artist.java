package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Artist extends Users {


    private Integer phone;
    private String biographic;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<Exposition> expositions;

    @ManyToMany(mappedBy = "artists") // as instrucoes da tabela de uniao vao estar atreladas a categories, campo de recipe
    private List<Admirer> admirers = new ArrayList<>();

    @ManyToMany  // manyto many gera uma terceira tabela e o jointable fala como montar
    @JoinTable(name = "Artists_has_Style",  //nome
            joinColumns = @JoinColumn(name = "artist_id"),  //deste "lado" temos os id de receita
            inverseJoinColumns = @JoinColumn(name = "style_id"))  // do outro lado os id da categoria
    private List<Style> styles = new ArrayList<>();

    public Artist(String name, String email, String password, Boolean isArtist, Boolean isAdmirer, Integer phone, String biographic) {
        super(name, email, password, isArtist, isAdmirer);
        this.phone = phone;
        this.biographic = biographic;
    }

    public Artist(String name, String email, String password, Boolean isArtist, Boolean isAdmirer, Integer phone, String biographic, List<Admirer> admirers) {
        super(name, email, password, isArtist, isAdmirer);
        this.phone = phone;
        this.biographic = biographic;
        this.admirers = admirers;
    }
}

