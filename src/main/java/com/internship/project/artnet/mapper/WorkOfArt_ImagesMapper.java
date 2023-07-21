package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.model.AdmirerDTO;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.WorkOfArt_ImagesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WorkOfArt_ImagesMapper {
    WorkOfArt_ImagesMapper INSTANCE = Mappers.getMapper(WorkOfArt_ImagesMapper.class);
    WorkOfArt_ImagesDTO WorkOfArt_ImagesToWorkOfArt_ImagesDTO(WorkOfArt_Images workOfArt_Images);
    WorkOfArt_Images WorkOfArt_ImagesDTOToWorkOfArt_Images(WorkOfArt_ImagesDTO workOfArt_ImagesDTO);

}
