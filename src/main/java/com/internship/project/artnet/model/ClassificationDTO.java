package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationDTO {
    private Long id;
    private String name;

    @JsonProperty("classification_url")
    private String classificationUrl;

    public ClassificationDTO( String name) {
        this.name = name;
    }
}


