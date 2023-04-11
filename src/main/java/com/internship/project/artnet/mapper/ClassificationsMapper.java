package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.model.AdmirerDTO;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.ClassificationsDTO;
import com.internship.project.artnet.model.ClassificationsDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClassificationsMapper {
    ClassificationsMapper INSTANCE = Mappers.getMapper(ClassificationsMapper.class);

    ClassificationsDetailsDTO classificationsToClassificationsDTO(Classifications classification);
    Classifications classificationsDTOToClassifications(ClassificationsDetailsDTO classificationDetailsDTO);
    Classifications classificationsDTOToClassifications(ClassificationsDTO classificationDTO);
}
