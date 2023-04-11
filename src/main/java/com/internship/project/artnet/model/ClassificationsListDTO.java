package com.internship.project.artnet.model;

import java.util.List;

public class ClassificationsListDTO {
    List<ClassificationsDetailsDTO> classification = null;

    public ClassificationsListDTO(List<ClassificationsDetailsDTO> classification) {
        this.classification = classification;
    }
}
