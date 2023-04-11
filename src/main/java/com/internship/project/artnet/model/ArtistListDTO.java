package com.internship.project.artnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArtistListDTO {
    List<ArtistDetailsDTO> artists = null;

    public ArtistListDTO(List<ArtistDetailsDTO> users) {
        this.artists = artists;
    }
}
