package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdmirerDTO extends UsersDTO{
    private Boolean is_shark;

    @JsonProperty("admirer_url")
    private String admirerUrl;
}
