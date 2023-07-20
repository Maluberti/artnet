package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.mapper.ClassificationMapper;
import com.internship.project.artnet.mapper.WorkOfArtMapper;
import com.internship.project.artnet.mapper.WorkOfArt_ImagesMapper;
import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.WorkOfArtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(WorkOfArtController.BASE_URL)
@Api(value = "WorkOfArt")
public class WorkOfArtController {
    public static final String BASE_URL = "/workofart";

    private final WorkOfArtService workOfArtService;
    private final ClassificationMapper classificationMapper;
    private final WorkOfArtMapper workOfArtMapper;
    private final WorkOfArt_ImagesMapper workOfArtImagesMapper;

    public WorkOfArtController(WorkOfArtService workOfArtService, ClassificationMapper classificationMapper, WorkOfArtMapper workOfArtMapper, WorkOfArt_ImagesMapper workOfArtImagesMapper) {
        this.workOfArtService = workOfArtService;
        this.classificationMapper = classificationMapper;
        this.workOfArtMapper = workOfArtMapper;
        this.workOfArtImagesMapper = workOfArtImagesMapper;
    }
    @ApiOperation(value = "Return a list of WorkOfArt")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WorkOfArtListDTO getListOfWorkOfArt()
    {
        return new WorkOfArtListDTO(workOfArtService.getAllWorkOfArt()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Return a WorkOfArt by its id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public WorkOfArtDTO getWorkOfArtById(@PathVariable Long id){
        return toDTO(workOfArtService.getWorkOfArtById(id));
    }

    @ApiOperation(value = "Return a list of Images in WorkOfArt by its id")
    @GetMapping("/{workId}/images")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkOfArt_ImagesDTO> getImagesByWorkOfArtId(@PathVariable Long workId){
        List<WorkOfArt_ImagesDTO> workOfArtImagesDTOs = workOfArtService.getImagesByWorkOfArtId(workId)
                .stream()
                .map(works -> {
                    WorkOfArt_ImagesDTO workOfArtImagesDTO = workOfArtImagesMapper.WorkOfArt_ImagesToWorkOfArt_ImagesDTO(works);
                    workOfArtImagesDTO.setWorkOfArtImagesUrl(WorkOfArtController.BASE_URL + "/" + workId + "/images" );
                    return workOfArtImagesDTO;
                })
                .collect(Collectors.toList());
        return workOfArtImagesDTOs;

    }

    @ApiOperation(value = "Create new WorkOfArt")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkOfArtDTO createNewWorkOfArt(@RequestBody WorkOfArtCreateDTO workOfArtDTO){
        return toDTO(workOfArtService.createNewWorkOfArtWithoutImages(toWorkOfArt(workOfArtDTO)));
    }

    @ApiOperation(value = "Update WorkOfArt")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public WorkOfArtDTO updateWorkOfArt(@PathVariable Long id, @RequestBody WorkOfArtCreateDTO workOfArtDTO){
        return toDTO(workOfArtService.updateWorkOfArtById(id, toWorkOfArt(workOfArtDTO)));
    }

    @ApiOperation(value = "Patch WorkOfArt")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public WorkOfArtDTO patchWorkOfArt(@PathVariable Long id, @RequestBody WorkOfArtCreateDTO workOfArtDTO){
        return toDTO(workOfArtService.patchWorkOfArt(id, toWorkOfArt(workOfArtDTO)));
    }

    @ApiOperation(value = "Delete WorkOfArt")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkOfArt(@PathVariable Long id){
        workOfArtService.deleteWorkOfArtById(id);
    }



    //aux methods
    private String getWorkOfArtUrl(Long id) {
        return WorkOfArtController.BASE_URL + "/" + id;
    }

    private WorkOfArtDTO toDTO(WorkOfArt workOfArt){
        WorkOfArtDTO workOfArtDTO = workOfArtMapper.workOfArtToWorkOfArtDTO(workOfArt);
        workOfArtDTO.setWorkOfArtUrl(getWorkOfArtUrl(workOfArt.getId()));
        return workOfArtDTO;
    }

    private WorkOfArt toWorkOfArt(WorkOfArtDTO workOfArtDTO){return workOfArtMapper.workOfArtDTOToWorkOfArt(workOfArtDTO);}
    private WorkOfArt toWorkOfArt(WorkOfArtCreateDTO workOfArtDTO){return workOfArtMapper.workOfArtDTOToWorkOfArt(workOfArtDTO);}
}
