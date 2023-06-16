package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    private List<WorkOfArt> work = new ArrayList<>();

    @ManyToOne
    private Artist artist;

    @ManyToMany(mappedBy = "expositions") // as instrucoes da tabela de uniao vao estar atreladas a categories, campo de recipe
    private List<Admirer> admirers = new ArrayList<>();

    public Exposition( String name, String concept, String inspiration, LocalDate startDate, LocalDate endDate, Artist artist) {
        this.name = name;
        this.concept = concept;
        this.inspiration = inspiration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.artist = artist;
    }

    public Exposition(String name, String concept, String inspiration, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.concept = concept;
        this.inspiration = inspiration;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
