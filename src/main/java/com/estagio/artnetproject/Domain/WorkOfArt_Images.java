package com.estagio.artnetproject.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WorkOfArt_Images {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Byte[] image;
}

// talvez nao precise so usar conjunto