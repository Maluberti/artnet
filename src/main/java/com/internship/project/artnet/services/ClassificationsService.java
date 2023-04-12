package com.internship.project.artnet.services;

import com.internship.project.artnet.model.ClassificationDTO;
import com.internship.project.artnet.model.ClassificationDetailsDTO;

import java.util.List;

public interface ClassificationsService {
    List<ClassificationDetailsDTO> getAllClassifications();

    ClassificationDetailsDTO getClassificationById(Long id);

    ClassificationDetailsDTO createNewClassification(ClassificationDTO classificationDTO);

    ClassificationDetailsDTO saveClassificationByDTO(Long id, ClassificationDetailsDTO classificationDTO);

    ClassificationDetailsDTO patchClassification(Long id, ClassificationDetailsDTO classificationDTO);

    void deleteClassificationById(Long id);
}
