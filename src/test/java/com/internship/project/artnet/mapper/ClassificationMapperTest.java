package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.model.ClassificationCreateDTO;
import com.internship.project.artnet.model.ClassificationDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassificationMapperTest {
    public static final Long ID = 1L;
    public static final String NAME = "testName";

    ClassificationMapper classificationMapper = ClassificationMapper.INSTANCE;

    @Test
    public void classificationToClassificationDTO() throws Exception{
        //given
        Classifications classification = new Classifications(NAME);
        classification.setId(ID);

        //when
        ClassificationDTO classificationDTO = classificationMapper.classificationsToClassificationsDTO(classification);

        //then
        assertEquals(classification.getId(), classificationDTO.getId());
        assertEquals(classification.getName(), classificationDTO.getName());
    }

    @Test
    public void classificationDTOToClassification()throws Exception{
        //given
        ClassificationDTO classificationDTO = new ClassificationDTO(NAME);
        classificationDTO.setId(ID);

        //when
        Classifications classification = classificationMapper.classificationsDTOToClassifications(classificationDTO);

        //then
        assertEquals(classificationDTO.getId(), classification.getId());
        assertEquals(classificationDTO.getName(), classification.getName());

    }
    @Test
    public void classificationCreateDTOToClassification()throws Exception{
        //given
        ClassificationCreateDTO classificationDTO = new ClassificationCreateDTO( NAME);

        //when
        Classifications classification = classificationMapper.classificationsDTOToClassifications(classificationDTO);

        //then
        assertEquals(classificationDTO.getName(), classification.getName());

    }

}
