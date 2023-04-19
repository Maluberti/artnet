package com.internship.project.artnet.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpositionDetailsListDTO {
    List<ExpositionDetailsDTO> exposition = null;

    public ExpositionDetailsListDTO(List<ExpositionDetailsDTO> exposition) {
        this.exposition = exposition;
    }
}
