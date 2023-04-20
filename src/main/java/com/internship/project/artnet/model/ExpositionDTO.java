package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpositionDTO {
    private Long id;
    private String name;
    private String concept;
    private String inspiration;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long artistId;

    @JsonProperty("exposition_url")
    private String expositionUrl;

    public ExpositionDTO(Long id, String name, String concept, String inspiration, LocalDate startDate, LocalDate endDate, Long artistId) {
        this.id = id;
        this.name = name;
        this.concept = concept;
        this.inspiration = inspiration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.artistId = artistId;
    }
}
