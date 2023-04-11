package com.internship.project.artnet.services;

import com.internship.project.artnet.controllers.AdmirerController;
import com.internship.project.artnet.controllers.ClassificationController;
import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.mapper.ClassificationsMapper;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.ClassificationsDTO;
import com.internship.project.artnet.model.ClassificationsDetailsDTO;
import com.internship.project.artnet.repositories.ClassificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ClassificationsServiceImpl implements ClassificationsService{
    @Autowired
    private final ClassificationsMapper classificationsMapper;

    @Autowired
    private final ClassificationsRepository classificationsRepository;

    public ClassificationsServiceImpl(ClassificationsMapper classificationsMapper, ClassificationsRepository classificationsRepository) {
        this.classificationsMapper = classificationsMapper;
        this.classificationsRepository = classificationsRepository;
    }

    @Override
    public List<ClassificationsDetailsDTO> getAllClassifications() {
        List<ClassificationsDetailsDTO> classificationsDetailsDTOS =
                classificationsRepository.findAll()
                        .stream()
                        .map(classifications -> {
                            ClassificationsDetailsDTO classificationsDetailsDTO = classificationsMapper.classificationsToClassificationsDTO(classifications);
                            classificationsDetailsDTO.setClassifUrl(getClassificationUrl(classifications.getId()));
                            return classificationsDetailsDTO;
                        })
                        .collect(Collectors.toList());

        return classificationsDetailsDTOS;
    }

    @Override
    public ClassificationsDetailsDTO getClassificationById(Long id) {
        return classificationsRepository.findById(id)
                .map(classificationsMapper::classificationsToClassificationsDTO)
                .map(classificationsDTO -> {
                    classificationsDTO.setClassifUrl(getClassificationUrl(id));
                    return classificationsDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ClassificationsDetailsDTO createNewClassification(ClassificationsDTO classificationDTO) {
        return saveAndReturnDTO(classificationsMapper.classificationsDTOToClassifications(classificationDTO));
    }

    @Override
    public ClassificationsDetailsDTO saveClassificationByDTO(Long id, ClassificationsDetailsDTO classificationsDetailsDTO) {
        Classifications classifications = classificationsMapper.classificationsDTOToClassifications(classificationsDetailsDTO);
        classifications.setId(id);

        return saveAndReturnDTO(classifications);
    }

    @Override
    public ClassificationsDetailsDTO patchClassification(Long id, ClassificationsDetailsDTO classificationsDetailsDTO) {
        return classificationsRepository.findById(id).map(admirer -> {

            if(classificationsDetailsDTO.getName() != null){
                admirer.setName(classificationsDetailsDTO.getName());
            }

            ClassificationsDetailsDTO returnDto = classificationsMapper.classificationsToClassificationsDTO(classificationsRepository.save(admirer));

            returnDto.setClassifUrl(getClassificationUrl(id));

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

    private ClassificationsDetailsDTO saveAndReturnDTO(Classifications classification) {
        Classifications savedClassification = classificationsRepository.save(classification);

        ClassificationsDetailsDTO returnDto = classificationsMapper.classificationsToClassificationsDTO(savedClassification);

        returnDto.setClassifUrl(getClassificationUrl(savedClassification.getId()));

        return returnDto;
    }
}
