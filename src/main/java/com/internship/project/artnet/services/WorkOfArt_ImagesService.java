package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.mapper.WorkOfArt_ImagesMapper;
import com.internship.project.artnet.repositories.WorkOfArt_ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class WorkOfArt_ImagesService {
    @Autowired
    private final WorkOfArt_ImagesRepository imagemObraDeArteRepository;

    @Autowired
    private final S3Service s3Service;

    public WorkOfArt_ImagesService(WorkOfArt_ImagesRepository imagemObraDeArteRepository, S3Service s3Service) {
        this.imagemObraDeArteRepository = imagemObraDeArteRepository;
        this.s3Service = s3Service;
    }

    // Construtor

    public WorkOfArt_Images saveImage(MultipartFile arquivo, WorkOfArt_Images work) throws IOException {
        String nomeArquivo = arquivo.getOriginalFilename();
        String caminhoS3 = s3Service.uploadImage(nomeArquivo, arquivo.getInputStream(), work.getWork().getId());

        WorkOfArt_Images image = new WorkOfArt_Images();
        image.setRoteS3(caminhoS3);
        // Configure outros atributos, se necessário


        return imagemObraDeArteRepository.save(image);

    }

    public ResponseEntity<InputStreamResource> downloadImage(String caminhoS3) {
        InputStream imageStream = s3Service.downloadImage(caminhoS3);
        // Crie uma ResponseEntity para retornar a imagem como resposta HTTP
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imageStream));
    }
}
