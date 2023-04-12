package com.internship.project.artnet.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String style;

    @ManyToMany(mappedBy = "styles")
    private Set<Artist> artists;
}

