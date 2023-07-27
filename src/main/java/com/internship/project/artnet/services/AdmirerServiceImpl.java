package com.internship.project.artnet.services;

import com.internship.project.artnet.controllers.AdmirerController;
import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.domain.Artist;
import com.internship.project.artnet.domain.Exposition;
import com.internship.project.artnet.domain.WorkOfArt;
import com.internship.project.artnet.mapper.AdmirerMapper;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.repositories.AdmirerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmirerServiceImpl implements AdmirerService{
    @Autowired
    private final AdmirerMapper admirerMapper;

    @Autowired
    private final AdmirerRepository admirerRepository;

    public AdmirerServiceImpl(AdmirerMapper admirerMapper, AdmirerRepository admirerRepository) {
        this.admirerMapper = admirerMapper;
        this.admirerRepository = admirerRepository;
    }

    @Override
    public List<Admirer> getAllAdmirers() {

        return admirerRepository.findAll();
    }

    @Override
    public Admirer getAdmirerById(Long id) {
        return admirerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admirer " + id + " not found!"));
    }

    @Override
    public Admirer createNewAdmirer(Admirer admirer) {
        admirer.setIsAdmirer(true);
        return admirerRepository.save(admirer);
    }

    @Override
    public Admirer updateAdmirerById(Long id, Admirer admirer) {
        admirerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admirer " + id + " not found!"));
        admirer.setId(id);

        return admirerRepository.save(admirer);
    }

    @Override
    public Admirer patchAdmirer(Long id, Admirer admirer) {
        return admirerRepository.findById(id).map(savedAdmirer -> {

            if(admirer.getName() != null){
                savedAdmirer.setName(admirer.getName());
            }

            if(admirer.getEmail() != null){
                savedAdmirer.setEmail(admirer.getEmail());
            }

            if(admirer.getPassword() != null){
                savedAdmirer.setPassword(admirer.getPassword());
            }

            if(admirer.getIsArtist() != null){
                savedAdmirer.setIsArtist(admirer.getIsArtist());
            }
            if(admirer.getIsAdmirer() != null){
                savedAdmirer.setIsAdmirer(admirer.getIsAdmirer());
            }
            if(admirer.getIs_shark() != null){
                savedAdmirer.setIs_shark(admirer.getIs_shark());
            }

            return admirerRepository.save(savedAdmirer);

        }).orElseThrow(() -> new ResourceNotFoundException("Admirer " + id + " not found!"));
    }

    @Override
    public void deleteAdmirerById(Long id) {
        admirerRepository.deleteById(id);
    }

    @Override
    public List<WorkOfArt> getAcquiredWorkOfArtsById(Long id) {
        Admirer admirer = admirerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admirer " + id + "not found!"));
        return (List<WorkOfArt>) admirer.getWorks();
    }

    @Override
    public List<Exposition> getVisitedExpositionsById(Long id) {
        Admirer admirer = admirerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admirer " + id + "not found!"));
        return (List<Exposition>) admirer.getExpositions();
    }

    @Override
    public List<Artist> getFavoriteArtistsById(Long id) {
        Admirer admirer = admirerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admirer " + id + "not found!"));
        return (List<Artist>) admirer.getArtists();
    }




}
