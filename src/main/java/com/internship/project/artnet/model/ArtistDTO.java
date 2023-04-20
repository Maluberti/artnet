package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDTO extends UsersDTO{
    private Integer phone;
    private String biographic;

    @JsonProperty("artist_url")
    private String artistUrl;

    public ArtistDTO(Long id, String name, String email, String password, Boolean isArtist, Boolean isAdmirer, Integer phone, String biographic) {
        super(id, name, email, password, isArtist, isAdmirer);
        this.phone = phone;
        this.biographic = biographic;
    }
}
