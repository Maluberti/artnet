package com.internship.project.artnet.services;

import com.internship.project.artnet.config.BucketName;
import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.repositories.WorkOfArt_ImagesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class WorkOfArt_ImagesServiceImpl implements WorkOfArt_ImagesService{
    private final FileStore fileStore;
    private final WorkOfArt_ImagesRepository repository;

    public WorkOfArt_ImagesServiceImpl(FileStore fileStore, WorkOfArt_ImagesRepository repository) {
        this.fileStore = fileStore;
        this.repository = repository;
    }


    @Override
    public WorkOfArt_Images saveImages(String title, String description, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                IMAGE_BMP.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then sav
        String path = String.format("%s", BucketName._IMAGE.getBucketName());
        System.out.println(path);
        String fileName = String.format("%s/%s", UUID.randomUUID() , file.getOriginalFilename());
        System.out.println( fileName);
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        WorkOfArt_Images image = WorkOfArt_Images.builder()
                .description(description)
                .title(title)
                .imagePath(path)
                .imageFileName(fileName)
                .build();
        repository.save(image);
        return repository.findByTitle(image.getTitle());
    }

    @Override
    public byte[] downloadImage(Long id) {
        WorkOfArt_Images image = repository.findById(id).get();
        return fileStore.download(image.getImagePath(), image.getImageFileName());
    }

    @Override
    public List<WorkOfArt_Images> getAllImages() {
        List<WorkOfArt_Images> images = new ArrayList<>();
        repository.findAll().forEach(images::add);
        return images;
    }
}
