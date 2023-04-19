package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Style;

import java.util.List;

public interface StyleService {
    List<Style> getAllStyle();

    Style getStyleById(Long id);

    Style createNewStyle(Style style);

    Style updateStyleById(Long id, Style style);

    Style patchStyle(Long id, Style style);

    void deleteStyleById(Long id);
}

