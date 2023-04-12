package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Style;
import com.internship.project.artnet.model.StyleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StyleMapper {
    StyleMapper INSTANCE = Mappers.getMapper(StyleMapper.class);

    StyleDTO styleToStyleDTO(Style style);
    Style styleDTOToStyle(StyleDTO styleDTO);


}
// @NullOrNotBlank(message = "Name must not be blank")