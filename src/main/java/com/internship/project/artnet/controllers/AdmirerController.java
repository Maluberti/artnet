package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.mapper.AdmirerMapper;
import com.internship.project.artnet.mapper.ArtistMapper;
import com.internship.project.artnet.mapper.ExpositionMapper;
import com.internship.project.artnet.mapper.WorkOfArtMapper;
import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.AdmirerService;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Tag(name = "Admirer")
@RestController
@RequestMapping(AdmirerController.BASE_URL)
public class AdmirerController {
    public static final String BASE_URL = "/users/admirers";
    private final AdmirerService admirerService;
    private final AdmirerMapper admirerMapper;
    private final ExpositionMapper expositionMapper;
    private final WorkOfArtMapper workOfArtMapper;
    private final ArtistMapper artistMapper;

    public AdmirerController(AdmirerService admirerService, AdmirerMapper admirerMapper, ExpositionMapper expositionMapper, WorkOfArtMapper workOfArtMapper, ArtistMapper artistMapper) {
        this.admirerService = admirerService;
        this.admirerMapper = admirerMapper;
        this.expositionMapper = expositionMapper;
        this.workOfArtMapper = workOfArtMapper;
        this.artistMapper = artistMapper;
    }
    @Operation(summary = "Return a list of Admirers")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AdmirerListDTO getListOfAdmirers()
    {
        return new AdmirerListDTO(admirerService.getAllAdmirers()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Return a Admirer by its id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AdmirerDTO getAdmirerById(@PathVariable Long id){
        return toDTO(admirerService.getAdmirerById(id));
    }

    @Operation(summary = "Return a list of WorkOfArts Acquired by Admirer id")
    @GetMapping({"/{id}/workOfArts"})
    @ResponseStatus(HttpStatus.OK)
    public WorkOfArtListDTO getAcquiredWorkOfArtsById(@PathVariable Long id){
        return new WorkOfArtListDTO( admirerService.getAcquiredWorkOfArtsById(id)
                .stream()
                .map(works -> {
                    WorkOfArtDTO workOfArtDTO = workOfArtMapper.workOfArtToWorkOfArtDTO(works);
                    workOfArtDTO.setWorkOfArtUrl(AdmirerController.BASE_URL + "/" + id + "workOfArts");
                    return workOfArtDTO;
                })
                .collect(Collectors.toList()));
    }

    @Operation(summary= "Return a list of Exposition visited by Admirer id")
    @GetMapping({"/{id}/expositions"})
    @ResponseStatus(HttpStatus.OK)
    public ExpositionListDTO getVisitedExpositionsById(@PathVariable Long id){
        return new ExpositionListDTO( admirerService.getVisitedExpositionsById(id)
                .stream()
                .map(exposition -> {
                    ExpositionDTO expositionDTO = expositionMapper.expositionToExpositionDTO(exposition);
                    expositionDTO.setExpositionUrl(AdmirerController.BASE_URL + "/" + id + "expositions");
                    return expositionDTO;
                })
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Return a list of Artists favorited by Admirer id")
    @GetMapping({"/{id}/artists"})
    @ResponseStatus(HttpStatus.OK)
    public ArtistListDTO getFavoriteArtistsById(@PathVariable Long id){
        return new ArtistListDTO( admirerService.getFavoriteArtistsById(id)
                .stream()
                .map(artist -> {
                    ArtistDTO artistDTO = artistMapper.artistToArtistDTO(artist);
                    artistDTO.setArtistUrl(AdmirerController.BASE_URL + "/" + id + "artists");
                    return artistDTO;
                })
                .collect(Collectors.toList()));
    }


    @Operation(summary = "Create new Admirer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdmirerDTO createNewAdmirer(@RequestBody AdmirerCreateDTO admirerDTO){
        return toDTO(admirerService.createNewAdmirer(toAdmirer(admirerDTO)));
    }

    @Operation(summary = "Update Admirer ")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AdmirerDTO updateAdmirer(@PathVariable Long id, @RequestBody AdmirerCreateDTO admirerDTO){
        return toDTO(admirerService.updateAdmirerById(id, toAdmirer(admirerDTO)));
    }

    @Operation(summary = "Patch Admirer ")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AdmirerDTO patchAdmirer(@PathVariable Long id, @RequestBody AdmirerCreateDTO admirerDTO){
        return toDTO(admirerService.patchAdmirer(id, toAdmirer(admirerDTO)));
    }

    @Operation(summary = "Delete  Admirer")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdmirer(@PathVariable Long id){
        admirerService.deleteAdmirerById(id);
    }



    //aux methods
    private AdmirerDTO toDTO (Admirer admirer){
        AdmirerDTO admirerDTO = admirerMapper.admirerToAdmirerDTO(admirer);
        admirerDTO.setAdmirerUrl(getAdmirerUrl(admirer.getId()));
        return admirerDTO;
    }
    private Admirer toAdmirer(AdmirerDTO admirerDTO){
        return admirerMapper.admirerDTOToAdmirer(admirerDTO);
    }

    private Admirer toAdmirer(AdmirerCreateDTO admirerDTO){
        return admirerMapper.admirerDTOToAdmirer(admirerDTO);
    }

    private String getAdmirerUrl(Long id) {
        return AdmirerController.BASE_URL + "/" + id;
    }
}
