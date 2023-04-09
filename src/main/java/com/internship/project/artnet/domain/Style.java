package com.internship.project.artnet.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String style;

    @ManyToMany(mappedBy = "styles") // as instrucoes da tabela de uniao vao estar atreladas a categories, campo de recipe
    private Set<Artist> artists;
}

