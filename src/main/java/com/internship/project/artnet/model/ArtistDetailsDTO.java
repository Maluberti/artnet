package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.domain.Style;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDetailsDTO extends ArtistDTO {

    private List<ExpositionDTO> expositions;
    private List<StyleDTO> styles;

}
