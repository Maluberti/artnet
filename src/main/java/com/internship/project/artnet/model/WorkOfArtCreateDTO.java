package com.internship.project.artnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOfArtCreateDTO {
    private String name;
    private String concept;
    private Double price;
    private Long classificationId;
    private Long expositionId;

}
