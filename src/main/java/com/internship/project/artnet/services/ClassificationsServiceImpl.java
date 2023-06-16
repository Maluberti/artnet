package com.internship.project.artnet.services;

import com.internship.project.artnet.controllers.ClassificationController;
import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.mapper.ClassificationMapper;
import com.internship.project.artnet.model.ClassificationDTO;
import com.internship.project.artnet.model.ClassificationDetailsDTO;
import com.internship.project.artnet.repositories.ClassificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ClassificationsServiceImpl implements ClassificationsService {
    @Autowired
    private final ClassificationMapper classificationMapper;

    @Autowired
    private final ClassificationsRepository classificationsRepository;

    public ClassificationsServiceImpl(ClassificationMapper classificationMapper, ClassificationsRepository classificationsRepository) {
        this.classificationMapper = classificationMapper;
        this.classificationsRepository = classificationsRepository;
    }

    @Override
    public List<Classifications> getAllClassifications() {
        return classificationsRepository.findAll();
    }

    @Override
    public Classifications getClassificationById(Long id) {
        return classificationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classification " + id + " not found!"));
    }

    @Override
    public Classifications createNewClassification(Classifications classification) {
        return classificationsRepository.save(classification);
    }

    @Override
    public Classifications updateClassificationById(Long id, Classifications classification) {
            classificationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classification " + id + " not found!"));
            classification.setId(id);

            return classificationsRepository.save(classification);

    }

    @Override
    public Classifications patchClassification(Long id, Classifications classification) {
        return classificationsRepository.findById(id).map(savedClassification -> {

            if (classification.getName() != null) {
                savedClassification.setName(classification.getName());
            }

            return classificationsRepository.save(savedClassification);

        }).orElseThrow(() -> new ResourceNotFoundException("Classification " + id + " not found!"));
    }

    @Override
    public void deleteClassificationById(Long id) {
        classificationsRepository.deleteById(id);
    }

    // aux methods
    private String getClassificationUrl(Long id) {
        return ClassificationController.BASE_URL + "/" + id;
    }

}
