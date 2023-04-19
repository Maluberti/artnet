package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.model.ArtistCreateDTO;
import com.internship.project.artnet.model.ArtistDTO;
import com.internship.project.artnet.model.ArtistDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {StyleMapper.class, ExpositionMapper.class})
public interface ArtistMapper {
    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    ArtistDetailsDTO artistToArtistDTO(Artist artist);
    Artist artistDTOToArtist(ArtistCreateDTO artistCreateDTO);
    Artist artistDTOToArtist(ArtistDTO artistDTO);
    Artist artistDTOToArtist(ArtistDetailsDTO artistDTO);

}
