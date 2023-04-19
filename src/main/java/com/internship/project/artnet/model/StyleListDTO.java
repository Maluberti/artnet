package com.internship.project.artnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class StyleListDTO {
    List<StyleDTO> style = null;

    public StyleListDTO(List<StyleDTO> style) {
        this.style = style;
    }
}
