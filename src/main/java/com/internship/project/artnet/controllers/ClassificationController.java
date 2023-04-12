package com.internship.project.artnet.controllers;

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
    public ClassificationListDTO getListOfClassifications()
    {
        return new ClassificationListDTO(classificationsService.getAllClassifications());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationDetailsDTO getClassificationById(@PathVariable Long id){
        return classificationsService.getClassificationById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassificationDetailsDTO createNewClassification(@RequestBody ClassificationDTO classificationDTO){
        return classificationsService.createNewClassification(classificationDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationDetailsDTO updateClassification(@PathVariable Long id, @RequestBody ClassificationDetailsDTO classificationDTO){
        return classificationsService.saveClassificationByDTO(id, classificationDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationDetailsDTO patchClassification(@PathVariable Long id, @RequestBody ClassificationDetailsDTO classificationDTO){
        return classificationsService.patchClassification(id, classificationDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteClassifications(@PathVariable Long id){
        classificationsService.deleteClassificationById(id);
    }

}
