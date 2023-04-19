package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.domain.Exposition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOfArtDTO {
    private Long id;
    private String name;
    private String concept;
    private Double price;
    private ClassificationDTO classification;
    private Long expositionId;

    @JsonProperty("work_url")
    private String workOfArtUrl;

}
