package com.internship.project.artnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArtistDetailsListDTO {
    List<ArtistDetailsDTO> artists = null;

    public ArtistDetailsListDTO(List<ArtistDetailsDTO> artists){this.artists = artists;}
}
