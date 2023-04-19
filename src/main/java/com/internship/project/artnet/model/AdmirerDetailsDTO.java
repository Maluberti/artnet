package com.internship.project.artnet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmirerDetailsDTO extends AdmirerDTO{

    private List<WorkOfArtDTO> work;
    private List<ArtistDTO> artists;
    private List<ExpositionDTO> expositions;

}
