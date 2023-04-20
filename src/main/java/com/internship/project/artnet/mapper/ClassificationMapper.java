package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.model.ClassificationCreateDTO;
import com.internship.project.artnet.model.ClassificationDTO;
import com.internship.project.artnet.model.ClassificationDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClassificationMapper {
    ClassificationMapper INSTANCE = Mappers.getMapper(ClassificationMapper.class);

    ClassificationDTO classificationsToClassificationsDTO(Classifications classification);
    Classifications classificationsDTOToClassifications(ClassificationCreateDTO classificationCreateDTO);
    Classifications classificationsDTOToClassifications(ClassificationDetailsDTO classificationDetailsDTO);
    Classifications classificationsDTOToClassifications(ClassificationDTO classificationDTO);
}
