package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkOfArt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String concept;
    private Double price;


    @ManyToOne
    private Classifications classification;
    @ManyToOne
    private Admirer admirer;
    @ManyToOne
    @JoinColumn(name = "exposition_id")
    private Exposition exposition;
    @OneToMany( mappedBy = "work") //mappedby define quem eh a tabela pai -- nesse caso recipe eh a tabela pai e na relacao manytoone quem recebe a chave estrangeira eh ingredients que eh a tabela filho
    private Set<WorkOfArt_Images> images = new HashSet<>();

    public WorkOfArt(String name, String concept, Double price, Classifications classification, Exposition exposition) {
        this.name = name;
        this.concept = concept;
        this.price = price;
        this.classification = classification;
        this.exposition = exposition;
    }
}
