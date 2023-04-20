package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StyleCreateDTO {
    private String style;

    @JsonProperty("style_url")
    private String styleUrl;

    public StyleCreateDTO(String style) {
        this.style = style;
    }
}
