package com.internship.project.artnet.services;

import com.internship.project.artnet.config.BucketName;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.repositories.WorkOfArtRepository;
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
    private final WorkOfArtRepository workOfArtRepository;

    public WorkOfArt_ImagesServiceImpl(FileStore fileStore, WorkOfArt_ImagesRepository repository, WorkOfArtRepository workOfArtRepository) {
        this.fileStore = fileStore;
        this.repository = repository;
        this.workOfArtRepository = workOfArtRepository;
    }


    @Override
    public List<WorkOfArt_Images> saveImages(Long workId, List<MultipartFile> files) {
        if (files.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        for(MultipartFile file: files){
            if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                    IMAGE_BMP.getMimeType(),
                    IMAGE_GIF.getMimeType(),
                    IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
                throw new IllegalStateException("Some file uploaded is not an image");
            }
        }

        String path = String.format("%s", BucketName._IMAGE.getBucketName());
        WorkOfArt work = workOfArtRepository.getReferenceById(workId);
        List<WorkOfArt_Images> images = new ArrayList<>();

        for(MultipartFile file: files){
            //get file metadata
            Map<String, String> metadata = new HashMap<>();
            metadata.put("Content-Type", file.getContentType());
            metadata.put("Content-Length", String.valueOf(file.getSize()));
            String fileName = String.format("%s/%s", workId , file.getOriginalFilename());
            System.out.println( fileName);
            try {
                fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
            } catch (IOException e) {
                throw new IllegalStateException("Failed to upload file", e);
            }

            WorkOfArt_Images image = new WorkOfArt_Images();
            image.setImagePath(path);
            image.setImageFileName(fileName);
            image.setWork(work);
            repository.save(image);
            images.add(image);


        }

        //Save Image in S3 and then sav
        work.setImages(images);

        return images;
    }

    @Override
    public byte[] downloadImage(Long id) {
        WorkOfArt_Images image = repository.findById(id).get();
        return fileStore.download(image.getImagePath(), image.getImageFileName());
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<WorkOfArt_Images> getAllImages() {
        List<WorkOfArt_Images> images = new ArrayList<>();
        repository.findAll().forEach(images::add);
        return images;
    }
}
