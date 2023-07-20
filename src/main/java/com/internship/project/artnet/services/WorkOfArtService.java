package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.model.WorkOfArt_ImagesDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface WorkOfArtService {
    List<WorkOfArt> getAllWorkOfArt();

    WorkOfArt getWorkOfArtById(Long id);

    WorkOfArt createNewWorkOfArtWithoutImages(WorkOfArt work);

    WorkOfArt updateWorkOfArtById(Long id, WorkOfArt work);

    WorkOfArt patchWorkOfArt(Long id, WorkOfArt work);

    void deleteWorkOfArtById(Long id);

    Classifications getClassificationByWorkOfArtId(Long id);

    List<WorkOfArt_Images> getImagesByWorkOfArtId(Long workId);
}

