package com.internship.project.artnet.model;

import com.internship.project.artnet.domain.WorkOfArt;

import java.util.List;

public class WorkOfArtListDTO {
    List<WorkOfArtDTO> works = null;

    public WorkOfArtListDTO(List<WorkOfArtDTO> works) {
        this.works = works;
    }
}
