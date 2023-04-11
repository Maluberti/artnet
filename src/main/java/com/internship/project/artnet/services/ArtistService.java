package com.internship.project.artnet.services;

import com.internship.project.artnet.model.AdmirerDTO;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.ArtistDTO;
import com.internship.project.artnet.model.ArtistDetailsDTO;

import java.util.List;

public interface ArtistService {
    List<ArtistDetailsDTO> getAllArtist();

    ArtistDetailsDTO getArtistById(Long id);

    ArtistDetailsDTO createNewArtist(ArtistDTO artistDTO);

    ArtistDetailsDTO saveArtistByDTO(Long id, ArtistDetailsDTO artistDetailsDTO);

    ArtistDetailsDTO patchArtist(Long id, ArtistDetailsDTO artistDetailsDTO);

    void deleteArtistById(Long id);
}
