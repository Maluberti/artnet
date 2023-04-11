package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.domain.WorkOfArt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmirerDetailsDTO extends UsersDTO{
    private Boolean is_shark;
    private Set<WorkOfArt> work = new HashSet<>();
    private Set<Artist> artists = new HashSet<>();
    private Set<Exposition> expositions = new HashSet<>();

    @JsonProperty("admirer_url")
    private String admirerUrl;

}
