package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.domain.Style;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDetailsDTO extends UsersDTO{
    private Integer phone;
    private String biographic;
    private Set<Exposition> expositions = new HashSet<>();
    private Set<Admirer> admirers;
    private Set<Style> styles = new HashSet<>();

    @JsonProperty("artist_url")
    private String artistUrl;
}
