package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.repositories.ArtistRepository;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceImplTest {
    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistServiceImpl artistService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllArtist() {
        Artist artist1 = new Artist("John", "john@example.com", "password", true, false, 1234567890, "biography");
        Artist artist2 = new Artist("Jane", "jane@example.com", "password", true, false, 1234567890, "biography");

        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist1);
        artistList.add(artist2);

        Mockito.when(artistRepository.findAll()).thenReturn(artistList);

        List<Artist> result = artistService.getAllArtist();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Jane", result.get(1).getName());
    }

    @Test
    public void testGetArtistById() {
        Artist artist = new Artist("John", "john@example.com", "password", true, false, 1234567890, "biography");
        artist.setId(1L);

        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));

        Artist result = artistService.getArtistById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("password", result.getPassword());
        assertEquals(true, result.getIsArtist());
        assertEquals(false, result.getIsAdmirer());
        assertEquals(1234567890, result.getPhone().intValue());
        assertEquals("biography", result.getBiographic());
    }

    @Test
    public void testCreateNewArtist() {
        Artist artist = new Artist("John", "john@example.com", "password", true, false, 1234567890, "biography");
        artist.setId(1L);

        Mockito.when(artistRepository.save(artist)).thenReturn(artist);

        Artist result = artistService.createNewArtist(artist);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("password", result.getPassword());
        assertEquals(true, result.getIsArtist());
        assertEquals(false, result.getIsAdmirer());
        assertEquals(1234567890, result.getPhone().intValue());
        assertEquals("biography", result.getBiographic());
    }

    @Test
    public void testUpdateArtistById() {
        Artist artist = new Artist("John", "john@example.com", "password", true, false, 1234567890, "biography");
        artist.setId(1L);
        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));


        Artist updatedArtist = new Artist("Johnny", "johnny@example.com", "newpassword", true, false, 1234567890, "newbiography");

        Mockito.when(artistRepository.save(updatedArtist)).thenReturn(updatedArtist);
        Artist result = artistService.updateArtistById(1L, updatedArtist);

        assertNotNull(result);
        assertEquals("Johnny", result.getName());
        assertEquals("johnny@example.com", result.getEmail());
        assertEquals("newpassword", result.getPassword());
        assertEquals(true, result.getIsArtist());
        assertEquals(false, result.getIsAdmirer());
        assertEquals(1234567890, result.getPhone().intValue());
        assertEquals("newbiography", result.getBiographic());
    }

    @Test
    public void testUpdateArtistByIdNotFound() {
        // Arrange
        Long id = 1L;
        Artist artistToUpdate = new Artist("John", "john@gmail.com", "password", true, false, 123456789, "Biography");

        Mockito.when(artistRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            artistService.updateArtistById(id, artistToUpdate);
        });

        Mockito.verify(artistRepository, Mockito.times(1)).findById(id);
        Mockito.verify(artistRepository, Mockito.times(0)).save(Mockito.any(Artist.class));
    }

    @Test
    public void testPatchArtist() {
        Artist artist = new Artist("John Doe", "john.doe@example.com", "password", true, false, 123456789, "Biography");
        artist.setId(1L);

        Artist artistUpdate = new Artist();
        artistUpdate.setBiographic("New Biography");

        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        Mockito.when(artistRepository.save(Mockito.any(Artist.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Artist updatedArtist = artistService.patchArtist(1L, artistUpdate);

        assertNotNull(updatedArtist);
        assertEquals(updatedArtist.getId(), artist.getId());
        assertEquals(updatedArtist.getBiographic(), artistUpdate.getBiographic());

        Mockito.verify(artistRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(artistRepository, Mockito.times(1)).save(Mockito.any(Artist.class));
        Mockito.verifyNoMoreInteractions(artistRepository);
    }


}

