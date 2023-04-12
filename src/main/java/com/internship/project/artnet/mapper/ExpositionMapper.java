package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.model.ArtistDTO;
import com.internship.project.artnet.model.ArtistDetailsDTO;
import com.internship.project.artnet.model.ExpositionDTO;
import com.internship.project.artnet.model.ExpositionDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {WorkOfArtMapper.class, ArtistMapper.class})
public interface ExpositionMapper {
    ExpositionMapper INSTANCE = Mappers.getMapper(ExpositionMapper.class);

    ExpositionDetailsDTO expositionToExpositionDTO(Exposition exposition);
    Exposition expositionDTOToExposition(ExpositionDTO expositionDTO);
    Exposition expositionDTOToExposition(ExpositionDetailsDTO expositionDTO);
}
