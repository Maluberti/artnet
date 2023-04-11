package com.internship.project.artnet.controllers;

import com.internship.project.artnet.model.AdmirerDTO;
import com.internship.project.artnet.model.AdmirerDetailsDTO;
import com.internship.project.artnet.model.AdmirerListDTO;
import com.internship.project.artnet.model.UsersDTO;
import com.internship.project.artnet.services.AdmirerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdmirerController.BASE_URL)
public class AdmirerController {
    public static final String BASE_URL = "/users/admirers";
    private final AdmirerService admirerService;

    public AdmirerController(AdmirerService admirerService) {
        this.admirerService = admirerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AdmirerListDTO getListOfAdmirers()
    {
        return new AdmirerListDTO(admirerService.getAllAdmirers());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AdmirerDetailsDTO getAdmirerById(@PathVariable Long id){
        return admirerService.getAdmirerById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdmirerDetailsDTO createNewAdmirer(@RequestBody AdmirerDTO admirerDTO){
        return admirerService.createNewAdmirer(admirerDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AdmirerDetailsDTO updateAdmirer(@PathVariable Long id, @RequestBody AdmirerDetailsDTO admirerDTO){
        return admirerService.saveAdmirerByDTO(id, admirerDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AdmirerDetailsDTO patchAdmirer(@PathVariable Long id, @RequestBody AdmirerDetailsDTO admirerDTO){
        return admirerService.patchAdmirer(id, admirerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdmirer(@PathVariable Long id){
        admirerService.deleteAdmirerById(id);
    }
}
