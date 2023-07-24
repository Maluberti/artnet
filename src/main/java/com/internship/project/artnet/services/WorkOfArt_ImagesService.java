package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.WorkOfArt_Images;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkOfArt_ImagesService {
    WorkOfArt_Images saveImages (String title, String description, MultipartFile file);

    byte[] downloadImage(Long id);

    List<WorkOfArt_Images> getAllImages();
}
