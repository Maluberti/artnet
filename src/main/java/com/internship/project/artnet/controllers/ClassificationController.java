package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.ClassificationsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


//arrumar nomenclatura
@RestController
@RequestMapping(ArtistController.BASE_URL)
public class ClassificationController {
    public static final String BASE_URL = "/classifications";
    private final ClassificationsService classificationsService;

    public ClassificationController(ClassificationsService classificationsService) {
        this.classificationsService = classificationsService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ClassificationsListDTO getListOfClassifications()
    {
        return new ClassificationsListDTO(classificationsService.getAllClassifications());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationsDetailsDTO getClassificationById(@PathVariable Long id){
        return classificationsService.getClassificationById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassificationsDetailsDTO createNewClassification(@RequestBody ClassificationsDTO classificationsDTO){
        return classificationsService.createNewClassification(classificationsDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationsDetailsDTO updateClassification(@PathVariable Long id, @RequestBody ClassificationsDetailsDTO classificationDTO){
        return classificationsService.saveClassificationByDTO(id, classificationDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationsDetailsDTO patchClassification(@PathVariable Long id, @RequestBody ClassificationsDetailsDTO classificationDTO){
        return classificationsService.patchClassification(id, classificationDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteClassifications(@PathVariable Long id){
        classificationsService.deleteClassificationById(id);
    }

}
