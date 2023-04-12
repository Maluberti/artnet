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
    private String name;
    private String concept;
    private String inspiration;
    private LocalDate startDate;
    private LocalDate endDate;

    @JsonProperty("exposition_url")
    private String expositionUrl;
}