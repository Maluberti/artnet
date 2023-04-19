package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmirerCreateDTO extends UserCreateDTO {
    private Boolean is_shark;

    @JsonProperty("admirer_url")
    private String admirerUrl;

    public AdmirerCreateDTO(String name, String email, String password, Boolean isArtist, Boolean isAdmirer, Boolean is_shark) {
        super(name, email, password, isArtist, isAdmirer);
        this.is_shark = is_shark;
    }
}
