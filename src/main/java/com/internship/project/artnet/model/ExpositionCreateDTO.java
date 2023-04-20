package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpositionCreateDTO {
    private String name;
    private String concept;
    private String inspiration;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long artistId;

    @JsonProperty("exposition_url")
    private String expositionUrl;

    public ExpositionCreateDTO(String name, String concept, String inspiration, LocalDate startDate, LocalDate endDate, Long artistId) {
        this.name = name;
        this.concept = concept;
        this.inspiration = inspiration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.artistId = artistId;
    }
}
