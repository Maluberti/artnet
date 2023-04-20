package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.model.ArtistCreateDTO;
import com.internship.project.artnet.model.ArtistDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArtistMapperTest {
    public static final Long ID = 1L;
    public static final String NAME = "testName";
    public static final String EMAIL = "testEmail";
    public static final String PASSWORD = "testPassword";
    public static final Boolean IS_ARTIST = true;
    public static final Boolean IS_ADMIRER = false;
    public static final Integer PHONE = 123445;
    public static final String BIO = "blablablablatest";

    ArtistMapper artistMapper = ArtistMapper.INSTANCE;

    @Test
    public void artistToArtistDTO() throws Exception{
        //given
        Artist artist = new Artist(NAME, EMAIL, PASSWORD, IS_ARTIST, IS_ADMIRER, PHONE, BIO);
        artist.setId(ID);

        //when
        ArtistDTO artistDTO = artistMapper.artistToArtistDTO(artist);

        //then
        assertEquals(artist.getId(), artistDTO.getId());
        assertEquals(artist.getName(), artistDTO.getName());
        assertEquals(artist.getEmail(), artistDTO.getEmail());
        assertEquals(artist.getPassword(), artistDTO.getPassword());
        assertEquals(artist.getIsArtist(), artistDTO.getIsArtist());
        assertEquals(artist.getIsAdmirer(), artistDTO.getIsAdmirer());
        assertEquals(artist.getPhone(), artistDTO.getPhone());
        assertEquals(artist.getBiographic(), artistDTO.getBiographic());

    }

    @Test
    public void artistDTOToArtist() throws Exception{
        //given
        ArtistDTO artistDTO = new ArtistDTO(ID, NAME, EMAIL, PASSWORD,  IS_ARTIST, IS_ADMIRER, PHONE, BIO);

        //when
        Artist artist = artistMapper.artistDTOToArtist(artistDTO);

        //then
        assertEquals(artistDTO.getId(), artist.getId());
        assertEquals(artistDTO.getName(), artist.getName());
        assertEquals(artistDTO.getEmail(), artist.getEmail());
        assertEquals(artistDTO.getPassword(), artist.getPassword());
        assertEquals(artistDTO.getIsArtist(), artist.getIsArtist());
        assertEquals(artistDTO.getIsAdmirer(), artist.getIsAdmirer());
        assertEquals(artistDTO.getPhone(), artist.getPhone());
        assertEquals(artistDTO.getBiographic(), artist.getBiographic());

    }
    @Test
    public void artistCreateDTOToArtist() throws Exception{
        //given
        ArtistCreateDTO artistDTO = new ArtistCreateDTO(NAME, EMAIL, PASSWORD,  IS_ARTIST, IS_ADMIRER, PHONE, BIO);

        //when
        Artist artist = artistMapper.artistDTOToArtist(artistDTO);

        //then
        assertEquals(artistDTO.getName(), artist.getName());
        assertEquals(artistDTO.getEmail(), artist.getEmail());
        assertEquals(artistDTO.getPassword(), artist.getPassword());
        assertEquals(artistDTO.getIsArtist(), artist.getIsArtist());
        assertEquals(artistDTO.getIsAdmirer(), artist.getIsAdmirer());
        assertEquals(artistDTO.getPhone(), artist.getPhone());
        assertEquals(artistDTO.getBiographic(), artist.getBiographic());

    }
}
