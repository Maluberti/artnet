package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.mapper.ArtistMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import com.internship.project.artnet.mapper.ExpositionMapper;
import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.ArtistService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(ArtistController.BASE_URL)
@Tag(name = "Artist")
public class ArtistController {
    public static final String BASE_URL = "/users/artists";
    private final ArtistService artistService;
    private final ArtistMapper artistMapper;
    private final ExpositionMapper expositionMapper;


    public ArtistController(ArtistService artistService, ArtistMapper artistMapper, ExpositionMapper expositionMapper) {
        this.artistService = artistService;
        this.artistMapper = artistMapper;
        this.expositionMapper = expositionMapper;
    }
    @Operation(summary = "Return a list of Artists")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArtistDetailsListDTO getListOfArtist()
    {
        return new ArtistDetailsListDTO(artistService.getAllArtist()
                .stream()
                .map(this::toDetailsDTO)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Return a Artist by its id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ArtistDetailsDTO getArtistById(@PathVariable Long id){
        return toDetailsDTO(artistService.getArtistById(id));
    }

    @Operation(summary = "Return a list of Expositions of the Artist by its id")
    @GetMapping({"/{id}/expositions"})
    @ResponseStatus(HttpStatus.OK)
    public ExpositionListDTO getAllExpositionsByArtistId(@PathVariable Long id){

        return new ExpositionListDTO(artistService.getAllExpositionsByArtistId(id)
                .stream()
                .map(exposition -> {
                    ExpositionDTO expositionDTO = expositionMapper.expositionToExpositionDTO(exposition);
                    expositionDTO.setExpositionUrl(AdmirerController.BASE_URL + "/" + id + "/expositions/"+ exposition.getId());
                    return expositionDTO;
                })
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Create a new Artist")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistDTO createNewArtist(@RequestBody ArtistCreateDTO artistCreateDTO) {
        return toDTO(artistService.createNewArtist(toArtist(artistCreateDTO)));
    }

    @Operation(summary = "update Artist")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ArtistDTO updateArtist(@PathVariable Long id, @RequestBody ArtistCreateDTO artistDTO){ //uso create pq nao precisa passar id
        return toDTO(artistService.updateArtistById(id, toArtist(artistDTO)));
    }

    @Operation(summary = "Patch Artist")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ArtistDTO patchArtist(@PathVariable Long id, @RequestBody ArtistCreateDTO artistDTO){
        return toDTO(artistService.patchArtist(id, toArtist(artistDTO)));
    }

    @Operation(summary = "Delete a Artist By its id")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteArtist(@PathVariable Long id){
        artistService.deleteArtistById(id);
    }

    // aux methods
    private String getArtistUrl(Long id) {
        return ArtistController.BASE_URL + "/" + id;
    }

    private ArtistDTO toDTO (Artist artist){
        ArtistDTO artistDTO = artistMapper.artistToArtistDTO((artist));
        artistDTO.setArtistUrl((getArtistUrl(artist.getId())));
        return artistDTO;
    }

    private ArtistDetailsDTO toDetailsDTO (Artist artist){
        ArtistDetailsDTO artistDTO = artistMapper.artistToArtistDTO((artist));
        artistDTO.setArtistUrl((getArtistUrl(artist.getId())));
        return artistDTO;
    }

    private Artist toArtist(ArtistCreateDTO artistDTO){
        return artistMapper.artistDTOToArtist(artistDTO);
    }

    private Artist toArtist(ArtistDTO artistDTO){
        return artistMapper.artistDTOToArtist(artistDTO);
    }

    private Exposition toExposition(ExpositionDTO expositionDTO){return expositionMapper.expositionDTOToExposition(expositionDTO);}
}
