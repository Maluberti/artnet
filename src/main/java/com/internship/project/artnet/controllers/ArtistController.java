package com.internship.project.artnet.controllers;

import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ArtistController.BASE_URL)
public class ArtistController {
    public static final String BASE_URL = "/users/artists";
    private final ArtistService artistService;


    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArtistListDTO getListOfArtist()
    {
        return new ArtistListDTO(artistService.getAllArtist());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ArtistDetailsDTO getArtistById(@PathVariable Long id){
        return artistService.getArtistById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistDetailsDTO createNewArtist(@RequestBody ArtistDTO artistDTO){
        return artistService.createNewArtist(artistDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ArtistDetailsDTO updateArtist(@PathVariable Long id, @RequestBody ArtistDetailsDTO artistDTO){
        return artistService.saveArtistByDTO(id, artistDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ArtistDetailsDTO patchArtist(@PathVariable Long id, @RequestBody ArtistDetailsDTO artistDTO){
        return artistService.patchArtist(id, artistDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteArtist(@PathVariable Long id){
        artistService.deleteArtistById(id);
    }

}
