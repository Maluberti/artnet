package com.internship.project.artnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArtistListDTO {
    List<ArtistDTO> artists = null;

    public ArtistListDTO(List<ArtistDTO> artists) {
        this.artists = artists;
    }
}
