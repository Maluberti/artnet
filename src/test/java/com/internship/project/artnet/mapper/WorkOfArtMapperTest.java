package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.model.ClassificationDTO;
import com.internship.project.artnet.model.WorkOfArtCreateDTO;
import com.internship.project.artnet.model.WorkOfArtDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WorkOfArtMapperTest {

    @Mock
    private ClassificationMapper classificationMapper;
    @InjectMocks
    private WorkOfArtMapper workOfArtMapper = Mappers.getMapper(WorkOfArtMapper.class);

    public static final Long ID = 1L;
    public static final String NAME = "testName";
    public static final String CONCEPT = "testEmail";
    public static final Double PRICE = 104.5;
    public static final Classifications CLASSIFICATION = new Classifications("testclass");
    public static final LocalDate STARTDATE = LocalDate.of(2023, Month.JANUARY, 25);
    public static final LocalDate ENDDATE = LocalDate.of(2023, Month.FEBRUARY, 25);
    public static final Artist ARTIST = new Artist("testName", "testEmail","testPassword", true, false, 123,"Hello its me");
    public static final Exposition EXPOSITION = new Exposition("test","test","test",STARTDATE,ENDDATE,ARTIST);
    public static final ClassificationDTO CLASSIFICATION_DTO = new ClassificationDTO("testclass");

    @Test
    public void shouldMapWorkOfArtToWorkOfArtDTO()throws Exception{
        //given
        CLASSIFICATION.setId(ID);
        WorkOfArt workOfArt = new WorkOfArt(NAME,CONCEPT,PRICE);
        workOfArt.setClassification(CLASSIFICATION);
        workOfArt.setExposition(EXPOSITION);
        workOfArt.setId(ID);

        //when
        ClassificationDTO classificationDTO = new ClassificationDTO();
        classificationDTO.setId(ID);
        classificationDTO.setName(NAME);
        Mockito.when(classificationMapper.classificationsToClassificationsDTO(CLASSIFICATION)).thenReturn(classificationDTO);

        WorkOfArtDTO workOfArtDTO = workOfArtMapper.workOfArtToWorkOfArtDTO(workOfArt);

        //then
        assertEquals(workOfArt.getId(), workOfArtDTO.getId());
        assertEquals(workOfArt.getName(), workOfArtDTO.getName());
        assertEquals(workOfArt.getConcept(), workOfArtDTO.getConcept());
        assertEquals(workOfArt.getPrice(), workOfArtDTO.getPrice());
        assertEquals(classificationDTO, workOfArtDTO.getClassification());
        assertEquals(workOfArt.getExposition().getId(), workOfArtDTO.getExpositionId());

        Mockito.verify(classificationMapper).classificationsToClassificationsDTO(CLASSIFICATION);

    }

    @Test
    public void shouldMapWorkOfArtDTOToWorkOfArt()throws Exception{
        //given
        CLASSIFICATION_DTO.setId(ID);
        WorkOfArtDTO workOfArtDTO = new WorkOfArtDTO(NAME,CONCEPT,PRICE,CLASSIFICATION_DTO,EXPOSITION.getId());
        workOfArtDTO.setId(ID);

        //when
        Classifications classification = new Classifications();
        classification.setId(ID);
        classification.setName(NAME);
        Mockito.when(classificationMapper.classificationsDTOToClassifications(CLASSIFICATION_DTO)).thenReturn(classification);

        //when
        WorkOfArt workOfArt = workOfArtMapper.workOfArtDTOToWorkOfArt(workOfArtDTO);

        //then
        assertEquals(workOfArtDTO.getId(), workOfArt.getId());
        assertEquals(workOfArtDTO.getName(), workOfArt.getName());
        assertEquals(workOfArtDTO.getConcept(), workOfArt.getConcept());
        assertEquals(workOfArtDTO.getPrice(), workOfArt.getPrice());
        assertEquals(classification, workOfArt.getClassification());
        assertEquals(workOfArtDTO.getExpositionId(), workOfArt.getExposition().getId());

        Mockito.verify(classificationMapper).classificationsDTOToClassifications(CLASSIFICATION_DTO);

    }

    @Test
    public void shouldMapWorkOfArtCreateDTOToWorkOfArt()throws Exception{
        //given
        CLASSIFICATION_DTO.setId(ID);
        WorkOfArtCreateDTO workOfArtDTO = new WorkOfArtCreateDTO(NAME,CONCEPT,PRICE,CLASSIFICATION_DTO,EXPOSITION.getId());

        //when
        Classifications classification = new Classifications();
        classification.setId(ID);
        classification.setName(NAME);
        Mockito.when(classificationMapper.classificationsDTOToClassifications(CLASSIFICATION_DTO)).thenReturn(classification);

        //when
        WorkOfArt workOfArt = workOfArtMapper.workOfArtDTOToWorkOfArt(workOfArtDTO);

        //then
        assertEquals(workOfArtDTO.getName(), workOfArt.getName());
        assertEquals(workOfArtDTO.getConcept(), workOfArt.getConcept());
        assertEquals(workOfArtDTO.getPrice(), workOfArt.getPrice());
        assertEquals(classification, workOfArt.getClassification());
        assertEquals(workOfArtDTO.getExpositionId(), workOfArt.getExposition().getId());

        Mockito.verify(classificationMapper).classificationsDTOToClassifications(CLASSIFICATION_DTO);

    }


}
