package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOfArt_ImagesDTO {
    private Long id;
    private Byte[] image;
    @JsonProperty("workImages_url")
    private String workOfArtImagesUrl;
}
