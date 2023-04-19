package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOfArtCreateDTO {
    private String name;
    private String concept;
    private Double price;
    private ClassificationDTO classification;
    private Long expositionId;

    @JsonProperty("work_url")
    private String workOfArtUrl;
}
