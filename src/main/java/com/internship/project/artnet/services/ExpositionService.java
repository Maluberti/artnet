package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.domain.WorkOfArt;

import java.util.Arrays;
import java.util.List;

public interface ExpositionService {
    List<Exposition> getAllExpositions();

    Exposition getExpositionById(Long id);

    Exposition createNewExposition(Exposition exposition);

    Exposition updateExpositionById(Long id, Exposition exposition);

    Exposition patchExposition(Long id, Exposition exposition);

    void deleteExpositionById(Long id);

    List<WorkOfArt> getWorkOfArtsByExpositionId(Long id);
}



