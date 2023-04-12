package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Exposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String concept;
    private String inspiration;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exposition") //mappedby define quem eh a tabela pai -- nesse caso recipe eh a tabela pai e na relacao manytoone quem recebe a chave estrangeira eh ingredients que eh a tabela filho
    private Set<WorkOfArt> work = new HashSet<>();

    @ManyToOne
    private Artist artist;

    @ManyToMany(mappedBy = "expositions") // as instrucoes da tabela de uniao vao estar atreladas a categories, campo de recipe
    private Set<Admirer> admirers;

}
