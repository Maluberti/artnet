package com.estagio.artnetproject.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class WorkOfArt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String concept;
    private Date date_exp;
    private Double value;

    private Set<Byte[]> images;

    @ManyToOne
    private Classifications classification;
    @ManyToOne
    private Admirer admirer;
    @ManyToOne
    private Exposition exposition;

}
