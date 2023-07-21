package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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


    public WorkOfArt(String name, String concept, Double price) {
        this.name = name;
        this.concept = concept;
        this.price = price;
    }


}
