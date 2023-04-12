package com.internship.project.artnet.services;

import com.internship.project.artnet.controllers.ClassificationController;
import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.mapper.ClassificationMapper;
import com.internship.project.artnet.model.ClassificationDTO;
import com.internship.project.artnet.model.ClassificationDetailsDTO;
import com.internship.project.artnet.repositories.ClassificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ClassificationsServiceImpl implements ClassificationsService{
    @Autowired
    private final ClassificationMapper classificationMapper;

    @Autowired
    private final ClassificationsRepository classificationsRepository;

    public ClassificationsServiceImpl(ClassificationMapper classificationMapper, ClassificationsRepository classificationsRepository) {
        this.classificationMapper = classificationMapper;
        this.classificationsRepository = classificationsRepository;
    }

    @Override
    public List<ClassificationDetailsDTO> getAllClassifications() {
        List<ClassificationDetailsDTO> classificationDetailsDTOS =
                classificationsRepository.findAll()
                        .stream()
                        .map(classifications -> {
                            ClassificationDetailsDTO classificationDetailsDTO = classificationMapper.classificationsToClassificationsDTO(classifications);
                            classificationDetailsDTO.setClassiicationfUrl(getClassificationUrl(classifications.getId()));
                            return classificationDetailsDTO;
                        })
                        .collect(Collectors.toList());

        return classificationDetailsDTOS;
    }

    @Override
    public ClassificationDetailsDTO getClassificationById(Long id) {
        return classificationsRepository.findById(id)
                .map(classificationMapper::classificationsToClassificationsDTO)
                .map(classificationsDTO -> {
                    classificationsDTO.setClassiicationfUrl(getClassificationUrl(id));
                    return classificationsDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ClassificationDetailsDTO createNewClassification(ClassificationDTO classificationDTO) {
        return saveAndReturnDTO(classificationMapper.classificationsDTOToClassifications(classificationDTO));
    }

    @Override
    public ClassificationDetailsDTO saveClassificationByDTO(Long id, ClassificationDetailsDTO classificationDetailsDTO) {
        Classifications classifications = classificationMapper.classificationsDTOToClassifications(classificationDetailsDTO);
        classifications.setId(id);

        return saveAndReturnDTO(classifications);
    }

    @Override
    public ClassificationDetailsDTO patchClassification(Long id, ClassificationDetailsDTO classificationDetailsDTO) {
        return classificationsRepository.findById(id).map(admirer -> {

            if(classificationDetailsDTO.getName() != null){
                admirer.setName(classificationDetailsDTO.getName());
            }

            ClassificationDetailsDTO returnDto = classificationMapper.classificationsToClassificationsDTO(classificationsRepository.save(admirer));

            returnDto.setClassiicationfUrl(getClassificationUrl(id));

            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteClassificationById(Long id) {
        classificationsRepository.deleteById(id);
    }

    // aux methods
    private String getClassificationUrl(Long id) {
        return ClassificationController.BASE_URL + "/" + id;
    }

    private ClassificationDetailsDTO saveAndReturnDTO(Classifications classification) {
        Classifications savedClassification = classificationsRepository.save(classification);

        ClassificationDetailsDTO returnDto = classificationMapper.classificationsToClassificationsDTO(savedClassification);

        returnDto.setClassiicationfUrl(getClassificationUrl(savedClassification.getId()));

        return returnDto;
    }
}
