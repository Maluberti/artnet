package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.model.WorkOfArt_ImagesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {WorkOfArtMapper.class})
public interface WorkOfArt_ImagesMapper {
    WorkOfArt_ImagesMapper INSTANCE = Mappers.getMapper(WorkOfArt_ImagesMapper.class);
    @Mapping(target = "workName", source = "work.name")
    WorkOfArt_ImagesDTO toDTO(WorkOfArt_Images image);
}
