package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.project.artnet.domain.WorkOfArt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOfArt_ImagesDTO {

    private Long id;
    private String imagePath;
    private String imageFileName;
    private String workName;

    @JsonProperty("workImages_url")
    private String workOfArtImagesUrl;
}
