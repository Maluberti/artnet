package com.internship.project.artnet.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.repositories.WorkOfArtRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkOfArtServiceTest {

    @Mock
    private WorkOfArtRepository workOfArtRepository;

    @Mock
    private AmazonS3 amazonS3;

    @InjectMocks
    private WorkOfArtServiceImpl workOfArtService;

    @Test
    public void CreateWorkOfArtWithImages() throws IOException {
        // Given
        List<MultipartFile> images = new ArrayList<>();
        images.add(new MockMultipartFile("image1.jpg", new byte[10]));
        images.add(new MockMultipartFile("image2.jpg", new byte[20]));

        List<WorkOfArt_Images> workOfArtImages = new ArrayList<>();
        WorkOfArt_Images workImage1 = new WorkOfArt_Images();
        workImage1.setImageKey("chave1.jpg");
        workOfArtImages.add(workImage1);
        WorkOfArt_Images workImage2 = new WorkOfArt_Images();
        workImage2.setImageKey("chave2.jpg");
        workOfArtImages.add(workImage2);

        WorkOfArt workOfArt = new WorkOfArt();
        workOfArt.setName("Test1");
        workOfArt.setConcept("Concept1");
        workOfArt.setImages(workOfArtImages);

        when(workOfArtRepository.save(any(WorkOfArt.class))).thenReturn(workOfArt);
        when(amazonS3.putObject(any(PutObjectRequest.class))).thenReturn(null);

        // When
        WorkOfArt result = workOfArtService.createWorkOfArt(workOfArt, images);
        // Then
        assertNotNull(result);
        assertEquals("Test1", result.getName());
        assertEquals("Concept1", result.getConcept());
        assertEquals(2, result.getImages().size());
    }

}
