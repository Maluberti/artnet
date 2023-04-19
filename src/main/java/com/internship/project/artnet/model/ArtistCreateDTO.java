package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistCreateDTO extends UserCreateDTO{
    private Integer phone;
    private String biographic;

    @JsonProperty("artist_url")
    private String artistUrl;
}
