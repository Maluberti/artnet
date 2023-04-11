package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.AdmirerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdmirerMapper {
    AdmirerMapper INSTANCE = Mappers.getMapper(AdmirerMapper.class);

    AdmirerDetailsDTO admirerToAdmirerDTO(Admirer admirer);
    Admirer admirerDTOToAdmirer(AdmirerDetailsDTO admirerDetailsDTO);
    Admirer admirerDTOToAdmirer(AdmirerDTO admirerDTO);
}

