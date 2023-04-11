package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.project.artnet.domain.WorkOfArt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationsDetailsDTO {
    private Long id;
    private String name;
    private Set<WorkOfArt> work = new HashSet<>();

    @JsonProperty("classif_url")
    private String classifUrl;
}
