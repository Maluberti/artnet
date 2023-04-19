package com.internship.project.artnet.services;

import com.internship.project.artnet.controllers.WorkOfArtController;
import com.internship.project.artnet.domain.Classifications;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.domain.WorkOfArt_Images;
import com.internship.project.artnet.mapper.ClassificationMapper;
import com.internship.project.artnet.mapper.WorkOfArtMapper;
import com.internship.project.artnet.model.WorkOfArtDTO;
import com.internship.project.artnet.repositories.ClassificationsRepository;
import com.internship.project.artnet.repositories.WorkOfArtRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class WorkOfArtServiceImpl implements WorkOfArtService{

    @Autowired
    private final WorkOfArtRepository workOfArtRepository;

    public WorkOfArtServiceImpl(WorkOfArtMapper workOfArtMapper, WorkOfArtRepository workOfArtRepository, ClassificationMapper classificationMapper, ClassificationsRepository classificationsRepository) {
        this.workOfArtRepository = workOfArtRepository;
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
    public WorkOfArt createNewWorkOfArt(WorkOfArt work) {
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
        return (List<WorkOfArt_Images>) workOfArt.getImages();
    }


}
