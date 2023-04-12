package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.project.artnet.domain.WorkOfArt_Images;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOfArtDetailsDTO extends WorkOfArtDTO{

    @JsonProperty("images")
    private List<WorkOfArt_ImagesDTO> images;
}
