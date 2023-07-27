package com.internship.project.artnet.services;
import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.repositories.AdmirerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdmirerServiceTest {

    @Mock
    private AdmirerRepository admirerRepository;

    @InjectMocks
    private AdmirerServiceImpl admirerService;

    @Test
    public void testGetAllAdmirers() {
        // Arrange
        List<Admirer> admirers = new ArrayList<>();
        admirers.add(new Admirer("John", "john@test.com", "1234", false, true, true));
        admirers.add(new Admirer("Mike", "mike@test.com", "5678", false, true, false));
        Mockito.when(admirerRepository.findAll()).thenReturn(admirers);

        // Act
        List<Admirer> result = admirerService.getAllAdmirers();

        // Assert
        assertEquals(admirers.size(), result.size());
        assertEquals(admirers.get(0).getName(), result.get(0).getName());
        assertEquals(admirers.get(1).getName(), result.get(1).getName());
    }

    @Test
    public void testGetAdmirerById() {
        // Given
        Admirer admirer = new Admirer("John", "john@test.com", "1234", false, true, true);
        Mockito.when(admirerRepository.findById(1L)).thenReturn(Optional.of(admirer));

        // When
        Admirer result = admirerService.getAdmirerById(1L);

        // Then
        assertEquals(admirer.getName(), result.getName());
    }

    @Test
    public void testGetAdmirerByIdNotFound() {
        // Given
        Mockito.when(admirerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> admirerService.getAdmirerById(1234L));
    }

    @Test
    public void testCreateNewAdmirer() {
        // Arrange
        Admirer admirer = new Admirer("John", "john@test.com", "1234", false, true, true);
        Mockito.when(admirerRepository.save(Mockito.any(Admirer.class))).thenReturn(admirer);

        // Act
        Admirer result = admirerService.createNewAdmirer(admirer);

        // Assert
        assertEquals(admirer.getName(), result.getName());
    }

    @Test
    public void testUpdateAdmirerById() {
        // Arrange
        Admirer admirer = new Admirer("John", "john@test.com", "1234", false, true, true);
        Mockito.when(admirerRepository.findById(1L)).thenReturn(Optional.of(admirer));
        admirer.setName("Mike");
        Mockito.when(admirerRepository.save(Mockito.any(Admirer.class))).thenReturn(admirer);

        // Act
        Admirer result = admirerService.updateAdmirerById(1L, admirer);

        // Assert
        assertEquals(admirer.getName(), result.getName());
    }

    @Test
    public void testUpdateAdmirerByIdNotFound() {
        Long id = 1L;
        // Arrange
        Admirer admirer = new Admirer("John", "john@test.com", "1234", false, true, true);
        Mockito.when(admirerRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        assertThrows(ResourceNotFoundException.class, () -> {
            admirerService.updateAdmirerById(id, admirer);
        });

        Mockito.verify(admirerRepository, Mockito.times(1)).findById(id);
        Mockito.verify(admirerRepository, Mockito.times(0)).save(Mockito.any(Admirer.class));
    }

    @Test
    public void testPatchAdmirer(){
        Admirer admirer = new Admirer("John", "john@test.com", "1234", false, true, true);
        admirer.setId(1L);

        Admirer admirerUpdate = new Admirer();
        admirerUpdate.setName("new Name");

        Mockito.when(admirerRepository.findById(1L)).thenReturn(Optional.of(admirer));
        Mockito.when(admirerRepository.save(Mockito.any(Admirer.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Admirer updatedAdmirer = admirerService.patchAdmirer(1L, admirerUpdate);

        assertNotNull(updatedAdmirer);
        assertEquals(updatedAdmirer.getId(), admirer.getId());
        assertEquals(updatedAdmirer.getName(), admirerUpdate.getName());

        Mockito.verify(admirerRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(admirerRepository, Mockito.times(1)).save(Mockito.any(Admirer.class));
        Mockito.verifyNoMoreInteractions(admirerRepository);


    }

    @Test
    public void testPatchArtistWithInvalidId() {
        Long invalidId = 100L;
        Admirer admirer = new Admirer("John", "john@test.com", "1234", false, true, true);

        Mockito.when(admirerRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            admirerService.patchAdmirer(invalidId, admirer);
        });

        Mockito.verify(admirerRepository, Mockito.times(1)).findById(invalidId);
        Mockito.verify(admirerRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void testDeleteArtistById() {
        Long admirerId = 1L;
        doNothing().when(admirerRepository).deleteById(admirerId);
        admirerService.deleteAdmirerById(admirerId);
        verify(admirerRepository, times(1)).deleteById(admirerId);

    }

    @Test
    public void testGetAcquiredWorkOfArtsById() {
        // Arrange
        Long admirerId = 1L;
        Admirer admirer = new Admirer("John", "john@test.com", "1234", false, true, true);
        admirer.setId( admirerId);

        List<WorkOfArt> workOfArts = new ArrayList<>();
        WorkOfArt workOfArt1 = new WorkOfArt("test1", "concept1");
        workOfArt1.setId(1L);

        WorkOfArt workOfArt2 = new WorkOfArt("test2", "concept2");
        workOfArt2.setId(2L);

        workOfArts.add(workOfArt1);
        workOfArts.add(workOfArt2);


        Mockito.when(admirerRepository.findById(admirerId)).thenReturn(Optional.of(admirer));

        // Act
        List<WorkOfArt> result = admirerService.getAcquiredWorkOfArtsById(admirerId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(workOfArt1));
        assertTrue(result.contains(workOfArt2));
    }

    @Test
    public void testVisitedExpositionsById() {
        // Arrange
        Long admirerId = 1L;
        Admirer admirer = new Admirer("John", "john@test.com", "1234", false, true, true);
        admirer.setId( admirerId);
        List<Admirer> admirerList = new ArrayList<>();
        admirerList.add(admirer);

        List<Exposition> expositions = new ArrayList<>();
        Exposition exposition1 = new Exposition("Name1", "testConcep1","testInsp", LocalDate.now(), LocalDate.now().plusDays(7));
        exposition1.setId(1L);
        exposition1.setAdmirers(admirerList);
        Exposition exposition2 = new Exposition("Name2", "testConcep2","testInsp",LocalDate.now().plusDays(10), LocalDate.now().plusDays(17));
        exposition2.setId(2L);
        exposition2.setAdmirers(admirerList);
        expositions.add(exposition1);
        expositions.add(exposition2);

        admirer.setExpositions(expositions);
        Mockito.when(admirerRepository.findById(admirerId)).thenReturn(Optional.of(admirer));

        // Act
        List<Exposition> result = admirerService.getVisitedExpositionsById(admirerId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(exposition1));
        assertTrue(result.contains(exposition2));
    }

    @Test
    public void testFavoriteArtistsById() {
        // Arrange
        Long admirerId = 1L;
        Admirer admirer = new Admirer("John", "john@test.com", "1234", false, true, true);
        admirer.setId( admirerId);
        List<Admirer> admirerList = new ArrayList<>();
        admirerList.add(admirer);

        List<Artist> artists = new ArrayList<>();
        Artist artist1 = new Artist("John", "john@example.com", "password", true, false, 12345678, "Biography", admirerList);
        artist1.setId(1L);
        Artist artist2 = new Artist("John2", "john2@example.com", "password2", true, false, 123456782, "Biography2", admirerList);
        artist2.setId(2L);
        artists.add(artist1);
        artists.add(artist2);
        admirer.setArtists(artists);
        Mockito.when(admirerRepository.findById(admirerId)).thenReturn(Optional.of(admirer));

        // Act
        List<Artist> result = admirerService.getFavoriteArtistsById(admirerId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(artist1));
        assertTrue(result.contains(artist2));
    }



}