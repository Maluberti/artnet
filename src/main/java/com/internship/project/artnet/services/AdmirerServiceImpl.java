package com.internship.project.artnet.services;

import com.internship.project.artnet.controllers.AdmirerController;
import com.internship.project.artnet.domain.Admirer;
import com.internship.project.artnet.mapper.AdmirerMapper;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.AdmirerDTO;
import com.internship.project.artnet.repositories.AdmirerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<AdmirerDetailsDTO> getAllAdmirers() {
        List<AdmirerDetailsDTO> admirerDetailsDTOS =
                admirerRepository.findAll()
                        .stream()
                        .map(admirer -> {
                            AdmirerDetailsDTO admirerDetailsDTO = admirerMapper.admirerToAdmirerDTO(admirer);
                            admirerDetailsDTO.setAdmirerUrl(getAdmirerUrl(admirer.getId()));
                            return admirerDetailsDTO;
                        })
                        .collect(Collectors.toList());

        return admirerDetailsDTOS;
    }

    @Override
    public AdmirerDetailsDTO getAdmirerById(Long id) {
        return admirerRepository.findById(id)
                .map(admirerMapper::admirerToAdmirerDTO)
                .map(admirerDTO -> {
                    admirerDTO.setAdmirerUrl(getAdmirerUrl(id));
                    return admirerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public AdmirerDetailsDTO createNewAdmirer(AdmirerDTO admirerDTO) {
        return saveAndReturnDTO(admirerMapper.admirerDTOToAdmirer(admirerDTO));
    }

    @Override
    public AdmirerDetailsDTO saveAdmirerByDTO(Long id, AdmirerDetailsDTO admirerDetailsDTO) {
        Admirer admirer = admirerMapper.admirerDTOToAdmirer(admirerDetailsDTO);
        admirer.setId(id);

        return saveAndReturnDTO(admirer);
    }

    @Override
    public AdmirerDetailsDTO patchAdmirer(Long id, AdmirerDetailsDTO admirerDetailsDTO) {
        return admirerRepository.findById(id).map(admirer -> {

            if(admirerDetailsDTO.getName() != null){
                admirer.setName(admirerDetailsDTO.getName());
            }

            if(admirerDetailsDTO.getEmail() != null){
                admirer.setEmail(admirerDetailsDTO.getEmail());
            }

            if(admirerDetailsDTO.getPassword() != null){
                admirer.setPassword(admirerDetailsDTO.getPassword());
            }

            if(admirerDetailsDTO.getIsArtist() != null){
                admirer.setIsArtist(admirerDetailsDTO.getIsArtist());
            }
            if(admirerDetailsDTO.getIsAdmirer() != null){
                admirer.setIsAdmirer(admirerDetailsDTO.getIsAdmirer());
            }
            if(admirerDetailsDTO.getIs_shark() != null){
                admirer.setIs_shark(admirerDetailsDTO.getIs_shark());
            }

            AdmirerDetailsDTO returnDto = admirerMapper.admirerToAdmirerDTO(admirerRepository.save(admirer));

            returnDto.setAdmirerUrl(getAdmirerUrl(id));

            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteAdmirerById(Long id) {
        admirerRepository.deleteById(id);
    }


    // aux methods
    private String getAdmirerUrl(Long id) {
        return AdmirerController.BASE_URL + "/" + id;
    }

    private AdmirerDetailsDTO saveAndReturnDTO(Admirer admirer) {
        Admirer savedAdmirer = admirerRepository.save(admirer);

        AdmirerDetailsDTO returnDto = admirerMapper.admirerToAdmirerDTO(savedAdmirer);

        returnDto.setAdmirerUrl(getAdmirerUrl(savedAdmirer.getId()));

        return returnDto;
    }
}
