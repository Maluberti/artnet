package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.WorkOfArt_Images;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkOfArt_ImagesService {
    List<WorkOfArt_Images> saveImages (Long workId, List<MultipartFile> files);

    byte[] downloadImage(Long id);

    List<WorkOfArt_Images> getAllImages();

    void deleteAll();
}
