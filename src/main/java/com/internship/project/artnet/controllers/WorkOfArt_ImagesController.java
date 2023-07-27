package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.mapper.WorkOfArt_ImagesMapper;
import com.internship.project.artnet.model.ClassificationListDTO;
import com.internship.project.artnet.model.WorkOfArt_ImagesDTO;
import com.internship.project.artnet.model.WorkOfArt_ImagesListDTO;
import com.internship.project.artnet.services.WorkOfArt_ImagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(WorkOfArt_ImagesController.BASE_URL)
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Images")
public class WorkOfArt_ImagesController {
    public static final String BASE_URL = "/workofart/images";
    private final WorkOfArt_ImagesMapper workOfArtImagesMapper;

    WorkOfArt_ImagesService service;

    @Operation(summary = "Return a list of images")
    @GetMapping
    public ResponseEntity<List<WorkOfArt_Images>> getImages() {
        return new ResponseEntity<>(service.getAllImages(), HttpStatus.OK);
    }

    @Operation(summary = "upload image")
    @PostMapping(
            path = "/{workid}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WorkOfArt_ImagesListDTO saveImage(@PathVariable("workid") Long workId,
                                         @RequestParam("file") List<MultipartFile> files) {
        return new WorkOfArt_ImagesListDTO(service.saveImages(workId, files)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "{id}/image/download")
    public byte[] downloadTodoImage(@PathVariable("id") Long id) {
        return service.downloadImage(id);
    }

    @Operation(summary = "Delete all")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllImages(){
        service.deleteAll();
    }

    private String getWorkOfArtImagesUrl(Long id) {
        return WorkOfArt_ImagesController.BASE_URL + "/" + id;
    }

    private WorkOfArt_ImagesDTO toDTO(WorkOfArt_Images image){
        WorkOfArt_ImagesDTO imageDTO = workOfArtImagesMapper.toDTO(image);
        imageDTO.setWorkOfArtImagesUrl(getWorkOfArtImagesUrl(image.getId()));
        return imageDTO;
    }

}
