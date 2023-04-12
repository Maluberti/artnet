package com.internship.project.artnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpositionListDTO {
    List<ExpositionDTO> exposition = null;

    public ExpositionListDTO(List<ExpositionDTO> exposition) {
        this.exposition = exposition;
    }
}
