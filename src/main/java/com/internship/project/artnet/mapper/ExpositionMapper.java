package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.ExpositionCreateDTO;
import com.internship.project.artnet.model.ExpositionDTO;
import com.internship.project.artnet.model.ExpositionDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {WorkOfArtMapper.class, ArtistMapper.class})
public interface ExpositionMapper {
    ExpositionMapper INSTANCE = Mappers.getMapper(ExpositionMapper.class);

    @Mapping(target = "artistId", source = "exposition.artist.id")
    ExpositionDetailsDTO expositionToExpositionDTO(Exposition exposition);
    @Mapping(target = "artist.id", source = "artistId")
    Exposition expositionDTOToExposition(ExpositionDTO expositionDTO);
    @Mapping(target = "artist.id", source = "artistId")
    Exposition expositionDTOToExposition(ExpositionDetailsDTO expositionDTO);
    @Mapping(target = "artist.id", source = "artistId")
    Exposition expositionDTOToExposition(ExpositionCreateDTO expositionDTO);
}
