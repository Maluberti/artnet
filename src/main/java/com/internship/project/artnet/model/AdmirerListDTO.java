package com.internship.project.artnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class  AdmirerListDTO {
    List<AdmirerDTO> admirers = null;

    public AdmirerListDTO(List<AdmirerDTO> admirers) {
        this.admirers = admirers;
    }

}
