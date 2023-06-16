package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.mapper.ClassificationMapper;
import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.ClassificationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;



@RestController
@RequestMapping(ClassificationController.BASE_URL)
@Api(value = "Classification")
public class ClassificationController {
    public static final String BASE_URL = "/classifications";
    private final ClassificationsService classificationsService;
    private final ClassificationMapper classificationMapper;

    public ClassificationController(ClassificationsService classificationsService, ClassificationMapper classificationMapper) {
        this.classificationsService = classificationsService;
        this.classificationMapper = classificationMapper;
    }

    @ApiOperation(value = "Return a list of Classifications")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ClassificationListDTO getListOfClassifications()
    {
        return new ClassificationListDTO(classificationsService.getAllClassifications()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Return an Classification by its id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationDTO getClassificationById(@PathVariable Long id){
        return toDTO(classificationsService.getClassificationById(id));
    }

    @ApiOperation(value = "Create new Classification")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassificationDTO createNewClassification(@RequestBody ClassificationCreateDTO classificationDTO){
        return toDTO(classificationsService.createNewClassification(toClassification(classificationDTO)));
    }

    @ApiOperation(value = "Update an Classification")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationDTO updateClassification(@PathVariable Long id, @RequestBody ClassificationCreateDTO classificationDTO){
        return toDTO(classificationsService.updateClassificationById(id, toClassification(classificationDTO)));
    }

    @ApiOperation(value = "Patch an Classification")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ClassificationDTO patchClassification(@PathVariable Long id, @RequestBody ClassificationCreateDTO classificationDTO){
        return toDTO(classificationsService.patchClassification(id, toClassification(classificationDTO)));
    }

    @ApiOperation(value = "Delete an Classification")
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
