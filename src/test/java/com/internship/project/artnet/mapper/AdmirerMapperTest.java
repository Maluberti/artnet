package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.model.AdmirerCreateDTO;
import com.internship.project.artnet.model.AdmirerDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdmirerMapperTest {
    public static final Long ID = 1L;
    public static final String NAME = "testName";
    public static final String EMAIL = "testEmail";
    public static final String PASSWORD = "testPassword";
    public static final Boolean IS_ARTIST = true;
    public static final Boolean IS_ADMIRER = false;
    public static final Boolean IS_SHARK = false;

    AdmirerMapper admirerMapper = AdmirerMapper.INSTANCE;

    @Test
    public void admirerToAdmirerDTO() throws Exception{
        //given
        Admirer admirer = new Admirer(NAME, EMAIL, PASSWORD, IS_ARTIST, IS_ADMIRER,IS_SHARK);
        admirer.setId(ID);

        //when
        AdmirerDTO admirerDTO = admirerMapper.admirerToAdmirerDTO(admirer);

        //then
        assertEquals(admirer.getId(), admirerDTO.getId());
        assertEquals(admirer.getName(), admirerDTO.getName());
        assertEquals(admirer.getEmail(), admirerDTO.getEmail());
        assertEquals(admirer.getPassword(), admirerDTO.getPassword());
        assertEquals(admirer.getIsArtist(), admirerDTO.getIsArtist());
        assertEquals(admirer.getIsAdmirer(), admirerDTO.getIsAdmirer());
        assertEquals(admirer.getIs_shark(), admirerDTO.getIs_shark());
    }

    @Test
    public void admirerDTOToAdmirer()throws Exception{
        //given
        AdmirerDTO admirerDTO = new AdmirerDTO(ID, NAME, EMAIL,PASSWORD, IS_ARTIST, IS_ADMIRER, IS_SHARK);

        //when
        Admirer admirer = admirerMapper.admirerDTOToAdmirer(admirerDTO);

        //then
        assertEquals(admirerDTO.getId(), admirer.getId() );
        assertEquals(admirerDTO.getName(), admirer.getName());
        assertEquals(admirerDTO.getEmail(), admirer.getEmail());
        assertEquals(admirerDTO.getPassword(), admirer.getPassword());
        assertEquals(admirerDTO.getIsArtist(), admirer.getIsArtist());
        assertEquals(admirerDTO.getIsAdmirer(), admirer.getIsAdmirer());
        assertEquals(admirerDTO.getIs_shark(), admirer.getIs_shark());
    }

    @Test
    public void admirerCreateDTOToAdmirer()throws Exception{
        //given
        AdmirerCreateDTO admirerDTO = new AdmirerCreateDTO(NAME, EMAIL,PASSWORD, IS_ARTIST, IS_ADMIRER, IS_SHARK);

        //when
        Admirer admirer = admirerMapper.admirerDTOToAdmirer(admirerDTO);

        //then
        assertEquals(admirerDTO.getName(), admirer.getName());
        assertEquals(admirerDTO.getEmail(), admirer.getEmail());
        assertEquals(admirerDTO.getPassword(), admirer.getPassword());
        assertEquals(admirerDTO.getIsArtist(), admirer.getIsArtist());
        assertEquals(admirerDTO.getIsAdmirer(), admirer.getIsAdmirer());
        assertEquals(admirerDTO.getIs_shark(), admirer.getIs_shark());
    }
}
