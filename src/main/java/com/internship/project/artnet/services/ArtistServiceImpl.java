package com.internship.project.artnet.services;

import com.internship.project.artnet.controllers.ArtistController;
import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.mapper.ArtistMapper;
import com.internship.project.artnet.model.ArtistDTO;
import com.internship.project.artnet.model.ExpositionListDTO;
import com.internship.project.artnet.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistMapper artistMapper, ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @Override
    public List<Artist> getAllArtist() {
        return artistRepository.findAll();
    }

    @Override
    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist " + id + " not found!"));
    }

    @Override
    public Artist createNewArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist updateArtistById(Long id, Artist artist) {
        artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist " + id + " not found!"));
        artist.setId(id);
        return artistRepository.save(artist);

    }

    @Override
    public Artist patchArtist(Long id, Artist artist) {
        return artistRepository.findById(id).map(savedArtist -> {

            if(artist.getName() != null){
                savedArtist.setName(artist.getName());
            }

            if(artist.getEmail() != null){
                savedArtist.setEmail(artist.getEmail());
            }

            if(artist.getPassword() != null){
                savedArtist.setPassword(artist.getPassword());
            }

            if(artist.getIsArtist() != null){
                savedArtist.setIsArtist(artist.getIsArtist());
            }
            if(artist.getIsAdmirer() != null){
                savedArtist.setIsAdmirer(artist.getIsAdmirer());
            }
            if(artist.getPhone() != null){
                savedArtist.setPhone(artist.getPhone());
            }
            if(artist.getBiographic() != null){
                savedArtist.setBiographic(artist.getBiographic());
            }

            return artistRepository.save(savedArtist);

        }).orElseThrow(() -> new ResourceNotFoundException("Artist " + id + " not found!"));
    }

    @Override
    public void deleteArtistById(Long id) {
        artistRepository.deleteById(id);

    }



    @Override
    public List<Exposition> getAllExpositionsByArtistId(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist" + id + "not found"));
        return (List<Exposition>) artist.getExpositions();
    }

}
