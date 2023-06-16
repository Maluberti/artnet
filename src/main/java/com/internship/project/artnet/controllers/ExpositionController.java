package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.mapper.ExpositionMapper;
import com.internship.project.artnet.mapper.WorkOfArtMapper;
import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.ExpositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(ExpositionController.BASE_URL)
@Api(value = "Exposition")
public class ExpositionController {
    public static final String BASE_URL = "/expositions";
    private final ExpositionService expositionService;
    private final ExpositionMapper expositionMapper;
    private final WorkOfArtMapper workOfArtMapper;

    public ExpositionController(ExpositionService expositionService, ExpositionMapper expositionMapper, WorkOfArtMapper workOfArtMapper) {
        this.expositionService = expositionService;
        this.expositionMapper = expositionMapper;
        this.workOfArtMapper = workOfArtMapper;
    }

    @ApiOperation(value = "Return a list of Exposition")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ExpositionDetailsListDTO getListOfExpositions(){
        return new ExpositionDetailsListDTO(expositionService.getAllExpositions()
                .stream()
                .map(this::toDetailsDTO)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Return a Exposition by its id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ExpositionDetailsDTO getExpositionById(@PathVariable Long id){
        return toDetailsDTO(expositionService.getExpositionById(id));
    }

    @ApiOperation(value = "Return a list of workOfArts in a Exposition by its id")
    @GetMapping({"/{id}/workOfArts"})
    @ResponseStatus(HttpStatus.OK)
    public WorkOfArtListDTO getWorkOfArtsByExpositionId(@PathVariable Long id){
        return new WorkOfArtListDTO( expositionService.getWorkOfArtsByExpositionId(id)
                .stream()
                .map(works -> {
                    WorkOfArtDTO workOfArtDTO = workOfArtMapper.workOfArtToWorkOfArtDTO(works);
                    workOfArtDTO.setWorkOfArtUrl(ExpositionController.BASE_URL + "/" + id + "workOfArts");
                    return workOfArtDTO;
                })
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Create a new Exposition")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpositionDTO createNewExposition(@RequestBody ExpositionCreateDTO expositionDTO){
        return toDTO(expositionService.createNewExposition(toExposition(expositionDTO)));
    }

    @ApiOperation(value = "Update a Exposition")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ExpositionDTO updateExposition(@PathVariable Long id, @RequestBody ExpositionCreateDTO expositionDTO){
        return toDTO(expositionService.updateExpositionById(id, toExposition(expositionDTO)));
    }

    @ApiOperation(value = "Patch a Exposition")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ExpositionDTO patchExposition(@PathVariable Long id, @RequestBody ExpositionCreateDTO expositionDTO){
        return toDTO(expositionService.patchExposition(id, toExposition(expositionDTO)));
    }

    @ApiOperation(value = "Delete a Exposition")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteExposition(@PathVariable Long id){
        expositionService.deleteExpositionById(id);
    }


    // aux methods
    private String getExpositionUrl(Long id) {
        return ExpositionController.BASE_URL + "/" + id;
    }

    private ExpositionDetailsDTO toDetailsDTO(Exposition exposition) {
        ExpositionDetailsDTO expositionDetailsDTO = expositionMapper.expositionToExpositionDTO(exposition);
        expositionDetailsDTO.setExpositionUrl(getExpositionUrl(exposition.getId()));
        return expositionDetailsDTO;
    }
    private ExpositionDTO toDTO (Exposition exposition){
        ExpositionDTO expositionDTO = expositionMapper.expositionToExpositionDTO(exposition);
        expositionDTO.setExpositionUrl(getExpositionUrl(exposition.getId()));
        return expositionDTO;
    }
    private  Exposition toExposition(ExpositionCreateDTO expositionDTO){return expositionMapper.expositionDTOToExposition(expositionDTO);}

    private  Exposition toExposition(ExpositionDTO expositionDTO){return expositionMapper.expositionDTOToExposition(expositionDTO);}
}
