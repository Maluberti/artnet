package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Admirer extends Users {
    private Boolean is_shark;

    @OneToMany( mappedBy = "admirer") //mappedby define quem eh a tabela pai -- nesse caso recipe eh a tabela pai e na relacao manytoone quem recebe a chave estrangeira eh ingredients que eh a tabela filho
    private List<WorkOfArt> work = new ArrayList<>();

    @ManyToMany  // manyto many gera uma terceira tabela e o jointable fala como montar
    @JoinTable(name = "Admirer_fav_Artists",  //nome
            joinColumns = @JoinColumn(name = "admirer_id"),  //deste "lado" temos os id de receita
            inverseJoinColumns = @JoinColumn(name = "artist_id"))  // do outro lado os id da categoria
    private List<Artist> artists = new ArrayList<>();

    @ManyToMany  // manyto many gera uma terceira tabela e o jointable fala como montar
    @JoinTable(name = "Admirer_visit_Exposition",  //nome
            joinColumns = @JoinColumn(name = "admirer_id"),  //deste "lado" temos os id de receita
            inverseJoinColumns = @JoinColumn(name = "exposition_id"))  // do outro lado os id da categoria
    private List<Exposition> expositions = new ArrayList<>();

    public Admirer(String name, String email, String password, Boolean isArtist, Boolean isAdmirer, Boolean is_shark) {
        super(name, email, password, isArtist, isAdmirer);
        this.is_shark = is_shark;
    }


}

