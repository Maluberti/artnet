package com.internship.project.artnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WorkOfArt_ImagesListDTO {
    List<WorkOfArt_ImagesDTO> images = null;

    public WorkOfArt_ImagesListDTO(List<WorkOfArt_ImagesDTO> images) {
        this.images = images;
    }
}
