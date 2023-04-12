package com.internship.project.artnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ClassificationListDTO {
    List<ClassificationDTO> classification = null;

    public ClassificationListDTO(List<ClassificationDTO> classification) {
        this.classification = classification;
    }
}
