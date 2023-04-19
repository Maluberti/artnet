package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.domain.WorkOfArt;

import java.util.Arrays;
import java.util.List;

public interface AdmirerService {
    List<Admirer> getAllAdmirers();

    Admirer getAdmirerById(Long id);

    Admirer createNewAdmirer(Admirer admirer);

    Admirer updateAdmirerById(Long id, Admirer admirer);

    Admirer patchAdmirer(Long id, Admirer admirer);

    void deleteAdmirerById(Long id);

    List<WorkOfArt> getAcquiredWorkOfArtsById(Long id);

    List<Exposition> getVisitedExpositionsById(Long id);

    List<Artist> getFavoriteArtistsById(Long id);
}

