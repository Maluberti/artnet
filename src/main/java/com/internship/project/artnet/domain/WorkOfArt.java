package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "work")
    private List<WorkOfArt_Images> images;

    @ManyToMany(mappedBy = "works")
    private List<Admirer> admirers;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "exposition_id")
    private Exposition exposition;

    @ManyToOne
    private Classifications classification;

    public WorkOfArt(String name, String concept) {
        this.name = name;
        this.concept = concept;
    }

    public WorkOfArt(Long id, String name, String concept) {
        this.id = id;
        this.name = name;
        this.concept = concept;
    }


}
