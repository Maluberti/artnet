package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.AdmirerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {WorkOfArt_ImagesMapper.class, ClassificationMapper.class})
public interface WorkOfArtMapper {
    WorkOfArtMapper INSTANCE = Mappers.getMapper(WorkOfArtMapper.class);

    @Mapping(target = "classification", source = "classification")
    @Mapping(target = "expositionId", source = "exposition.id")
    WorkOfArtDTO workOfArtToWorkOfArtDTO(WorkOfArt workOfArt);

    @Mapping(target = "exposition.id", source = "expositionId")
    WorkOfArt workOfArtDTOToWorkOfArt(WorkOfArtCreateDTO workOfArtCreateDTO);

    @Mapping(target = "exposition.id", source = "expositionId")
    WorkOfArt workOfArtDTOToWorkOfArt(WorkOfArtDTO WorkOfArtDTO);


}
