package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.Style;
import com.internship.project.artnet.mapper.StyleMapper;
import com.internship.project.artnet.model.*;
import com.internship.project.artnet.services.StyleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(StyleController.BASE_URL)
@Tag(name = "Style")
public class StyleController {
    public static final String BASE_URL = "/styles";
    private final StyleService styleService;
    private final StyleMapper styleMapper;

    public StyleController(StyleService styleService, StyleMapper styleMapper) {
        this.styleService = styleService;
        this.styleMapper = styleMapper;
    }

    @Operation(summary = "Return a list of Styles")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StyleListDTO getListOfStyles()
    {
        return new StyleListDTO(styleService.getAllStyle()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Return a Style by its id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StyleDTO getStyleById(@PathVariable Long id){
        return toDTO(styleService.getStyleById(id));
    }

    @Operation(summary = "Create new Style")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StyleDTO createNewStyle(@RequestBody StyleCreateDTO styleDTO){
        return toDTO(styleService.createNewStyle(toStyle(styleDTO)));
    }

    @Operation(summary = "Update a Style")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StyleDTO updateStyle(@PathVariable Long id, @RequestBody StyleCreateDTO styleDTO){
        return toDTO(styleService.updateStyleById(id, toStyle(styleDTO)));
    }

    @Operation(summary = "Patch a Style")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StyleDTO patchStyle(@PathVariable Long id, @RequestBody StyleCreateDTO styleDTO){
        return toDTO(styleService.patchStyle(id, toStyle(styleDTO)));
    }

    @Operation(summary = "Delete a Style")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteStyle(@PathVariable Long id){
        styleService.deleteStyleById(id);
    }


    //aux methods

    private String getStyleUrl(Long id) {
        return StyleController.BASE_URL + "/" + id;
    }

    private StyleDTO toDTO (Style style){
        StyleDTO styleDTO = styleMapper.styleToStyleDTO(style);
        styleDTO.setStyleUrl(getStyleUrl(style.getId()));
        return styleDTO;
    }

    private Style toStyle(StyleCreateDTO styleDTO){ return styleMapper.styleDTOToStyle(styleDTO);}

    private Style toStyle(StyleDTO styleDTO){ return styleMapper.styleDTOToStyle(styleDTO);}


}
