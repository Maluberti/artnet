package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.model.AdmirerDTO;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.WorkOfArtDTO;
import com.internship.project.artnet.model.WorkOfArtDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {WorkOfArt_ImagesMapper.class})
public interface WorkOfArtMapper {
    WorkOfArtMapper INSTANCE = Mappers.getMapper(WorkOfArtMapper.class);

    WorkOfArtDetailsDTO workOfArtToWorkOfArtDTO(WorkOfArt workOfArt);
    WorkOfArt workOfArtDTOToWorkOfArt(WorkOfArtDetailsDTO workOfArtDetailsDTO);
    WorkOfArt workOfArtDTOToWorkOfArt(WorkOfArtDTO WorkOfArtDTO);
}
