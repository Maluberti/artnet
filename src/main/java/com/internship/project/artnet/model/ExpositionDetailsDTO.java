package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.WorkOfArt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpositionDetailsDTO extends ExpositionDTO{
    private List<WorkOfArtDTO> work;
    private ArtistDTO artist;


}
