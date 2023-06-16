package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.mapper.ExpositionMapper;
import com.internship.project.artnet.repositories.ExpositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExpositionServiceImpl implements ExpositionService {

    @Autowired
    private final ExpositionMapper expositionMapper;

    @Autowired
    private final ExpositionRepository expositionRepository;

    public ExpositionServiceImpl(ExpositionMapper expositionMapper, ExpositionRepository expositionRepository) {
        this.expositionMapper = expositionMapper;
        this.expositionRepository = expositionRepository;
    }


    @Override
    public List<Exposition> getAllExpositions() {
        return expositionRepository.findAll();
    }

    @Override
    public Exposition getExpositionById(Long id) {
        return expositionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exposition " + id + " not found!"));
    }

    @Override
    public Exposition createNewExposition(Exposition exposition) {
        return expositionRepository.save(exposition);
    }

    @Override
    public Exposition updateExpositionById(Long id, Exposition exposition) {
        expositionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exposition " + id + " not found!"));
        exposition.setId(id);
        return expositionRepository.save(exposition);
    }

    @Override
    public Exposition patchExposition(Long id, Exposition exposition) {
        return expositionRepository.findById(id).map(savedExposition -> {

            if(exposition.getName() != null){
                savedExposition.setName(exposition.getName());
            }
            if(exposition.getConcept() != null){
                savedExposition.setConcept(exposition.getConcept());
            }
            if(exposition.getInspiration() != null){
                savedExposition.setInspiration(exposition.getInspiration());
            }
            if(exposition.getStartDate() != null){
                savedExposition.setStartDate(exposition.getStartDate());
            }
            if(exposition.getEndDate() != null){
                savedExposition.setEndDate(exposition.getEndDate());
            }

            return expositionRepository.save(savedExposition);

        }).orElseThrow(() -> new ResourceNotFoundException("Exposition " + id + " not found!"));
    }



    @Override
    public void deleteExpositionById(Long id) {
        expositionRepository.deleteById(id);

    }

    @Override
    public List<WorkOfArt> getWorkOfArtsByExpositionId(Long id) {
        Exposition exposition = expositionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exposition " + id + " not found!"));
        return (List<WorkOfArt>) exposition.getWork();

    }

}
