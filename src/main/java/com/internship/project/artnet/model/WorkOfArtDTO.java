package com.internship.project.artnet.model;

import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.domain.Exposition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOfArtDTO {
    private String name;
    private String concept;
    private Date date_exp;
    private Double price;

    private Classifications classification;
    private Admirer admirer;
    private Exposition exposition;

}
