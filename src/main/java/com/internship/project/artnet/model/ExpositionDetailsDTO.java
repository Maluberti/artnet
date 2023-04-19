package com.internship.project.artnet.model;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpositionDetailsDTO extends ExpositionDTO {
        private List<WorkOfArtDTO> work;

}

