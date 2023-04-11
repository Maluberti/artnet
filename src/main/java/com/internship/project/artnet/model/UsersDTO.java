package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    private Long Id;
    private String name;
    private String email;
    private String password;
    private Boolean isArtist;
    private Boolean isAdmirer;

    @JsonProperty("user_url")
    private String userUrl;

    public UsersDTO(Long Id, String name, String email, String password, Boolean isArtist, Boolean isAdmirer) {
        this.Id = Id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isArtist = isArtist;
        this.isAdmirer = isAdmirer;
    }
}
