package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.model.ExpositionListDTO;

import java.util.Arrays;
import java.util.List;

public interface ArtistService {
    List<Artist> getAllArtist();

    Artist getArtistById(Long id);

    Artist createNewArtist(Artist artist);

    Artist updateArtistById(Long id, Artist artist);

    Artist patchArtist(Long id, Artist artist);

    void deleteArtistById(Long id);

    List<Exposition> getAllExpositionsByArtistId(Long id);
}
