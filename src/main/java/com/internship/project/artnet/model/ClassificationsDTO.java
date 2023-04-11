package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationsDTO {
    private Long id;
    private String name;

    @JsonProperty("classif_url")
    private String classifUrl;

}
