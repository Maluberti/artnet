package com.internship.project.artnet.services;

import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.AdmirerDTO;

import java.util.List;

public interface AdmirerService {
    List<AdmirerDetailsDTO> getAllAdmirers();

    AdmirerDetailsDTO getAdmirerById(Long id);

    AdmirerDetailsDTO createNewAdmirer(AdmirerDTO admirerDTO);

    AdmirerDetailsDTO saveAdmirerByDTO(Long id, AdmirerDetailsDTO admirerDetailsDTO);

    AdmirerDetailsDTO patchAdmirer(Long id, AdmirerDetailsDTO admirerDetailsDTO);

    void deleteAdmirerById(Long id);
}
