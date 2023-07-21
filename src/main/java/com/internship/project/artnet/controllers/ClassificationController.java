package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.mapper.ClassificationMapper;
import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.ClassificationsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;



@RestController
@RequestMapping(ClassificationController.BASE_URL)
@Tag(name = "Classification")
public class ClassificationController {
    public static final String BASE_URL = "/classifications";
    private final ClassificationsService classificationsService;
    private final ClassificationMapper classificationMapper;

    public ClassificationController(ClassificationsService classificationsService, ClassificationMapper classificationMapper) {
        this.classificationsService = classificationsService;
        this.classificationMapper = classificationMapper;
    }

    @Operation(summary = "Return a list of Classifications")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ClassificationListDTO getListOfClassifications()
    {
        return new ClassificationListDTO(classificationsService.getAllClassifications()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Return an Classification by its id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationDTO getClassificationById(@PathVariable Long id){
        return toDTO(classificationsService.getClassificationById(id));
    }

    @Operation(summary = "Create new Classification")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassificationDTO createNewClassification(@RequestBody ClassificationCreateDTO classificationDTO){
        return toDTO(classificationsService.createNewClassification(toClassification(classificationDTO)));
    }

    @Operation(summary = "Update an Classification")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationDTO updateClassification(@PathVariable Long id, @RequestBody ClassificationCreateDTO classificationDTO){
        return toDTO(classificationsService.updateClassificationById(id, toClassification(classificationDTO)));
    }

    @Operation(summary = "Patch an Classification")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationDTO patchClassification(@PathVariable Long id, @RequestBody ClassificationCreateDTO classificationDTO){
        return toDTO(classificationsService.patchClassification(id, toClassification(classificationDTO)));
    }

    @Operation(summary = "Delete an Classification")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteClassifications(@PathVariable Long id){
        classificationsService.deleteClassificationById(id);
    }

    // aux methods
    private String getClassificationUrl(Long id) {
        return ClassificationController.BASE_URL + "/" + id;
    }

    private ClassificationDTO toDTO (Classifications classification){
        ClassificationDTO classificationDTO = classificationMapper.classificationsToClassificationsDTO(classification);
        classificationDTO.setClassificationUrl(getClassificationUrl(classification.getId()));
        return classificationDTO;
    }

    private Classifications toClassification(ClassificationCreateDTO classificationDTO) {return classificationMapper.classificationsDTOToClassifications(classificationDTO);}

    private Classifications toClassification(ClassificationDTO classificationDTO) {return classificationMapper.classificationsDTOToClassifications(classificationDTO);}

}
