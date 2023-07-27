package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.model.ExpositionCreateDTO;
import com.internship.project.artnet.model.ExpositionDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpositionMapperTest {
    public static final Long ID = 1L;
    public static final String NAME = "testName";
    public static final String CONCEPT = "testConcept";
    public static final String INSPIRATION = "test";
    public static final LocalDate STARTDATE = LocalDate.of(2023, Month.JANUARY, 25);
    public static final LocalDate ENDDATE = LocalDate.of(2023, Month.FEBRUARY, 25);
    public static final Artist ARTIST = new Artist("testName", "testEmail","testPassword", true, false, 123,"Hello its me");

    ExpositionMapper expositionMapper = ExpositionMapper.INSTANCE;
@Test
public void expositionToExpositionDTO()throws Exception{
    //given
    Exposition exposition = new Exposition( NAME, CONCEPT, INSPIRATION,STARTDATE,ENDDATE,ARTIST);
    exposition.setId(ID);

    //when
    ExpositionDTO expositionDTO = expositionMapper.expositionToExpositionDTO(exposition);

    //then
    assertEquals(exposition.getId(), expositionDTO.getId());
    assertEquals(exposition.getName(), expositionDTO.getName());
    assertEquals(exposition.getConcept(), expositionDTO.getConcept());
    assertEquals(exposition.getInspiration(), expositionDTO.getInspiration());
    assertEquals(exposition.getStartDate(), expositionDTO.getStartDate());
    assertEquals(exposition.getEndDate(), expositionDTO.getEndDate());
    assertEquals(exposition.getArtist().getId(), expositionDTO.getArtistId());

}

    @Test
    public void expositionDTOToExposition()throws Exception{
        //given
        Artist artist = new Artist("testName", "testEmail","testPassword", true, false, 123,"Hello its me");
        ExpositionDTO expositionDTO = new ExpositionDTO( ID, NAME, CONCEPT, INSPIRATION,STARTDATE,ENDDATE,artist.getId());


        //when
        Exposition exposition = expositionMapper.expositionDTOToExposition(expositionDTO);

        //then
        assertEquals(expositionDTO.getId(), exposition.getId());
        assertEquals(expositionDTO.getName(), exposition.getName());
        assertEquals(expositionDTO.getConcept(), exposition.getConcept());
        assertEquals(expositionDTO.getInspiration(), exposition.getInspiration());
        assertEquals(expositionDTO.getStartDate(), exposition.getStartDate());
        assertEquals(expositionDTO.getEndDate(), exposition.getEndDate());
        assertEquals(expositionDTO.getArtistId(), exposition.getArtist().getId());

    }

    @Test
    public void expositionCreateDTOToExposition()throws Exception{
        //given
        ExpositionCreateDTO expositionDTO = new ExpositionCreateDTO( NAME, CONCEPT, INSPIRATION,STARTDATE,ENDDATE,ARTIST.getId());


        //when
        Exposition exposition = expositionMapper.expositionDTOToExposition(expositionDTO);

        //then
        assertEquals(expositionDTO.getName(), exposition.getName());
        assertEquals(expositionDTO.getConcept(), exposition.getConcept());
        assertEquals(expositionDTO.getInspiration(), exposition.getInspiration());
        assertEquals(expositionDTO.getStartDate(), exposition.getStartDate());
        assertEquals(expositionDTO.getEndDate(), exposition.getEndDate());
        assertEquals(expositionDTO.getArtistId(), exposition.getArtist().getId());

    }
}