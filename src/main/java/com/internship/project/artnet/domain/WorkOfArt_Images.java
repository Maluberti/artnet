package com.internship.project.artnet.domain;

import jakarta.persistence.*;

@Entity
public class WorkOfArt_Images {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Byte[] image;

    @ManyToOne
    private WorkOfArt work;
}

