package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.WorkOfArt_Images;
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

@RestController
@RequestMapping(WorkOfArt_ImagesController.BASE_URL)
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Images")
public class WorkOfArt_ImagesController {
    public static final String BASE_URL = "/workofart/image";

    WorkOfArt_ImagesService service;

    @Operation(summary = "Return a list of images")
    @GetMapping
    public ResponseEntity<List<WorkOfArt_Images>> getImages() {
        return new ResponseEntity<>(service.getAllImages(), HttpStatus.OK);
    }

    @Operation(summary = "upload image")
    @PostMapping(
            path = "",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkOfArt_Images> saveImage(@RequestParam("title") String title,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(service.saveImages(title, description, file), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/image/download")
    public byte[] downloadTodoImage(@PathVariable("id") Long id) {
        return service.downloadImage(id);
    }

}
