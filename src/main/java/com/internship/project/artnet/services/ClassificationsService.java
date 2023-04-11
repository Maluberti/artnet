package com.internship.project.artnet.services;

import com.internship.project.artnet.model.AdmirerDTO;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.ClassificationsDTO;
import com.internship.project.artnet.model.ClassificationsDetailsDTO;

import java.util.List;

public interface ClassificationsService {
    List<ClassificationsDetailsDTO> getAllClassifications();

    ClassificationsDetailsDTO getClassificationById(Long id);

    ClassificationsDetailsDTO createNewClassification(ClassificationsDTO classificationDTO);

    ClassificationsDetailsDTO saveClassificationByDTO(Long id, ClassificationsDetailsDTO classificationDTO);

    ClassificationsDetailsDTO patchClassification(Long id, ClassificationsDetailsDTO classificationDTO);

    void deleteClassificationById(Long id);
}
