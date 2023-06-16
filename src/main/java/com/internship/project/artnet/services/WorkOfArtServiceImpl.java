package com.internship.project.artnet.services;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.repositories.WorkOfArtRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkOfArtServiceImpl implements WorkOfArtService{

    @Autowired
    private final WorkOfArtRepository workOfArtRepository;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private String bucketName;

    public WorkOfArtServiceImpl(WorkOfArtRepository workOfArtRepository, AmazonS3 amazonS3, String bucketName) {
        this.workOfArtRepository = workOfArtRepository;
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }


    @Override
    public List<WorkOfArt> getAllWorkOfArt() {
        return workOfArtRepository.findAll();
    }

    @Override
    public WorkOfArt getWorkOfArtById(Long id) {
        return workOfArtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work Of Art " + id + " not found!"));
    }

    @Override
    public WorkOfArt createNewWorkOfArtWithoutImages(WorkOfArt work) {
        return workOfArtRepository.save(work);
    }

    @Override
    public WorkOfArt createWorkOfArt(Long workId, List<MultipartFile> images) throws IOException {

        WorkOfArt work = workOfArtRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("Work Of Art " + workId + " not found!"));

        List<WorkOfArt_Images> imagesList = new ArrayList<>();
        for (MultipartFile image : images) {
            String imageKey = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();
            byte[] imageBytes = image.getBytes();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(image.getContentType());
            metadata.setContentLength(imageBytes.length);
            PutObjectRequest request = new PutObjectRequest(bucketName, imageKey, new ByteArrayInputStream(imageBytes), metadata);
            amazonS3.putObject(request);
            WorkOfArt_Images workOfArtImages = new WorkOfArt_Images();
            workOfArtImages.setImageKey(imageKey);
            workOfArtImages.setWork(work);
            imagesList.add(workOfArtImages);
        }
        work.setImages(imagesList);
        return workOfArtRepository.save(work);
    }


    @Override
    public WorkOfArt updateWorkOfArtById(Long id, WorkOfArt work) {
        workOfArtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work Of Art " + id + " not found!"));
        work.setId(id);
        return workOfArtRepository.save(work);
    }

    @Override
    public WorkOfArt patchWorkOfArt(Long id, WorkOfArt work) {
        return workOfArtRepository.findById(id).map(savedWork -> {

            if(work.getName() != null){
                savedWork.setName(work.getName());
            }
            if(work.getConcept() != null){
                savedWork.setConcept(work.getConcept());
            }
            if(work.getPrice() != null){
                savedWork.setPrice(work.getPrice());
            }
            return workOfArtRepository.save(savedWork);

        }).orElseThrow(() -> new ResourceNotFoundException("Work Of Art " + id + " not found!"));
    }

    @Override
    public void deleteWorkOfArtById(Long id) {
        workOfArtRepository.deleteById(id);

    }

    @Override
    public Classifications getClassificationByWorkOfArtId(Long id) {
        WorkOfArt workOfArt = workOfArtRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Work of art" + id + "not found"));

        return workOfArt.getClassification();

    }

    @Override
    public List<WorkOfArt_Images> getImagesByWorkOfArtId(Long workId) {
        WorkOfArt workOfArt = workOfArtRepository.findById(workId)
                .orElseThrow(() -> new EntityNotFoundException("Work of art" + workId + "not found"));

        return workOfArt.getImages();
    }


}
