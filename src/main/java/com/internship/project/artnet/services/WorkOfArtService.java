package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.domain.WorkOfArt;

import java.util.List;

public interface WorkOfArtService {
    List<WorkOfArt> getAllWorkOfArt();

    WorkOfArt getWorkOfArtById(Long id);

    WorkOfArt createNewWorkOfArtWithoutImages(WorkOfArt work);

    WorkOfArt updateWorkOfArtById(Long id, WorkOfArt work);

    WorkOfArt patchWorkOfArt(Long id, WorkOfArt work);

    void deleteWorkOfArtById(Long id);

    Classifications getClassificationByWorkOfArtId(Long id);


}

