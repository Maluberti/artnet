package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Classifications;

import java.util.List;

public interface ClassificationsService {
    List<Classifications> getAllClassifications();

    Classifications getClassificationById(Long id);

    Classifications createNewClassification(Classifications classification);

    Classifications updateClassificationById(Long id, Classifications classification);

    Classifications patchClassification(Long id, Classifications classification);

    void deleteClassificationById(Long id);
}
