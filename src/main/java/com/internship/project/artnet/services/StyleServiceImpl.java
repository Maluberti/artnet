package com.internship.project.artnet.services;

import com.internship.project.artnet.controllers.StyleController;
import com.internship.project.artnet.domain.Style;
import com.internship.project.artnet.mapper.StyleMapper;
import com.internship.project.artnet.model.StyleDTO;
import com.internship.project.artnet.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class StyleServiceImpl implements StyleService{

    @Autowired
    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }


    @Override
    public List<Style> getAllStyle() {

        return styleRepository.findAll();
    }

    @Override
    public Style getStyleById(Long id) {
        return styleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Style " + id + " not found!"));
    }

    @Override
    public Style createNewStyle(Style style) {
        return styleRepository.save(style);
    }

    @Override
    public Style updateStyleById(Long id, Style style) {
        styleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Style " + id + " not found!"));
        style.setId(id);
        return styleRepository.save(style);
    }

    @Override
    public Style patchStyle(Long id, Style style) {
        return styleRepository.findById(id).map(savedStyle -> {

            if(style.getStyle() != null){
                savedStyle.setStyle(style.getStyle());
            }

            return styleRepository.save(savedStyle);

        }).orElseThrow(() -> new ResourceNotFoundException("Style " + id + " not found!"));
    }

    @Override
    public void deleteStyleById(Long id) {
       styleRepository.deleteById(id);
    }



}
