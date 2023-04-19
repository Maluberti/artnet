package com.internship.project.artnet.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDetailsDTO extends ArtistDTO {

    private List<ExpositionDTO> expositions;
    private List<StyleDTO> styles;

}
