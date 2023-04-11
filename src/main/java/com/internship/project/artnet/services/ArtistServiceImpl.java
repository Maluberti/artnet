package com.internship.project.artnet.services;

import com.internship.project.artnet.controllers.AdmirerController;
import com.internship.project.artnet.controllers.ArtistController;
import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.mapper.ArtistMapper;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.ArtistDTO;
import com.internship.project.artnet.model.ArtistDetailsDTO;
import com.internship.project.artnet.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    private final ArtistMapper artistMapper;

    @Autowired
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistMapper artistMapper, ArtistRepository artistRepository) {
        this.artistMapper = artistMapper;
        this.artistRepository = artistRepository;
    }


    @Override
    public List<ArtistDetailsDTO> getAllArtist() {
        List<ArtistDetailsDTO> artistDetailsDTOS =
                artistRepository.findAll()
                        .stream()
                        .map(artist -> {
                            ArtistDetailsDTO artistDetailsDTO = artistMapper.artistToArtistDTO(artist);
                            artistDetailsDTO.setArtistUrl(getArtistUrl(artist.getId()));
                            return artistDetailsDTO;
                        })
                        .collect(Collectors.toList());

        return artistDetailsDTOS;
    }

    @Override
    public ArtistDetailsDTO getArtistById(Long id) {
        return artistRepository.findById(id)
                .map(artistMapper::artistToArtistDTO)
                .map(artistDTO -> {
                    artistDTO.setArtistUrl(getArtistUrl(id));
                    return artistDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ArtistDetailsDTO createNewArtist(ArtistDTO artistDTO) {
        return saveAndReturnDTO(artistMapper.artistDTOToArtist(artistDTO));
    }

    @Override
    public ArtistDetailsDTO saveArtistByDTO(Long id, ArtistDetailsDTO artistDetailsDTO) {
        Artist artist = artistMapper.artistDTOToArtist(artistDetailsDTO);
        artist.setId(id);

        return saveAndReturnDTO(artist);
    }

    @Override
    public ArtistDetailsDTO patchArtist(Long id, ArtistDetailsDTO artistDetailsDTO) {
        return artistRepository.findById(id).map(artist -> {

            if(artistDetailsDTO.getName() != null){
                artist.setName(artistDetailsDTO.getName());
            }

            if(artistDetailsDTO.getEmail() != null){
                artist.setEmail(artistDetailsDTO.getEmail());
            }

            if(artistDetailsDTO.getPassword() != null){
                artist.setPassword(artistDetailsDTO.getPassword());
            }

            if(artistDetailsDTO.getIsArtist() != null){
                artist.setIsArtist(artistDetailsDTO.getIsArtist());
            }
            if(artistDetailsDTO.getIsAdmirer() != null){
                artist.setIsAdmirer(artistDetailsDTO.getIsAdmirer());
            }
            if(artistDetailsDTO.getPhone() != null){
                artist.setPhone(artistDetailsDTO.getPhone());
            }
            if(artistDetailsDTO.getBiographic() != null){
                artist.setBiographic(artistDetailsDTO.getBiographic());
            }

            ArtistDetailsDTO returnDto = artistMapper.artistToArtistDTO(artistRepository.save(artist));

            returnDto.setArtistUrl(getArtistUrl(id));

            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteArtistById(Long id) {
        artistRepository.deleteById(id);

    }

    // aux methods
    private String getArtistUrl(Long id) {
        return ArtistController.BASE_URL + "/" + id;
    }

    private ArtistDetailsDTO saveAndReturnDTO(Artist artist) {
        Artist savedArtist = artistRepository.save(artist);

        ArtistDetailsDTO returnDto = artistMapper.artistToArtistDTO(savedArtist);

        returnDto.setArtistUrl(getArtistUrl(savedArtist.getId()));

        return returnDto;
    }
}
