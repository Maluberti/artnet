package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.mapper.WorkOfArt_ImagesMapper;
import com.internship.project.artnet.model.WorkOfArt_ImagesDTO;
import com.internship.project.artnet.services.WorkOfArt_ImagesService;
import io.swagger.annotations.Api;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(WorkOfArtController.BASE_URL)
@Api(value = "Image")
public class WorkOfArtImageController {

    public static final String BASE_URL = "/work/images";

    private final WorkOfArt_ImagesService imagemObraDeArteService;
    private final WorkOfArt_ImagesMapper workOfArtImagesMapper;

    public WorkOfArtImageController(WorkOfArt_ImagesService imagemObraDeArteService, WorkOfArt_ImagesMapper workOfArtImagesMapper) {
        this.imagemObraDeArteService = imagemObraDeArteService;
        this.workOfArtImagesMapper = workOfArtImagesMapper;
    }

    // Construtor

    @PostMapping("/{obraDeArteId}")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkOfArt_ImagesDTO uploadImage(@RequestParam("image") MultipartFile arquivo,
                                           @PathVariable WorkOfArt_ImagesDTO work) throws IOException {

        return toDTO(imagemObraDeArteService.saveImage(arquivo, toImages(work)));
    }

    @GetMapping("/{caminhoS3}")
    public ResponseEntity<InputStreamResource> downloadImagem(@PathVariable String caminhoS3) {
        return imagemObraDeArteService.downloadImage(caminhoS3);
    }

    private WorkOfArt_Images toImages (WorkOfArt_ImagesDTO imageDTO){
        return workOfArtImagesMapper.WorkOfArt_ImagesDTOToWorkOfArt_Images(imageDTO);
    }

    private WorkOfArt_ImagesDTO toDTO (WorkOfArt_Images work){
        WorkOfArt_ImagesDTO imagesDTO = workOfArtImagesMapper.WorkOfArt_ImagesToWorkOfArt_ImagesDTO(work);
        imagesDTO.setWorkOfArtImagesUrl(getImageUrl(work.getId()));
        return imagesDTO;
    }

    private String getImageUrl(Long id) {
        return WorkOfArtController.BASE_URL + "/" + id;
    }
}
