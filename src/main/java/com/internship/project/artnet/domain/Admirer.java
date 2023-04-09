package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Admirer extends Users {
    private Boolean is_shark;

    @OneToMany( mappedBy = "admirer") //mappedby define quem eh a tabela pai -- nesse caso recipe eh a tabela pai e na relacao manytoone quem recebe a chave estrangeira eh ingredients que eh a tabela filho
    private Set<WorkOfArt> work = new HashSet<>();

    @ManyToMany  // manyto many gera uma terceira tabela e o jointable fala como montar
    @JoinTable(name = "Admirer_fav_Artists",  //nome
            joinColumns = @JoinColumn(name = "admirer_id"),  //deste "lado" temos os id de receita
            inverseJoinColumns = @JoinColumn(name = "artist_id"))  // do outro lado os id da categoria
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany  // manyto many gera uma terceira tabela e o jointable fala como montar
    @JoinTable(name = "Admirer_visit_Exposition",  //nome
            joinColumns = @JoinColumn(name = "admirer_id"),  //deste "lado" temos os id de receita
            inverseJoinColumns = @JoinColumn(name = "exposition_id"))  // do outro lado os id da categoria
    private Set<Exposition> expositions = new HashSet<>();

}

