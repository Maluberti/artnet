package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StyleDTO {
    private Long id;
    private String style;

    @JsonProperty("style_url")
    private String styleUrl;

    public StyleDTO(String style) {
        this.style = style;
    }
}