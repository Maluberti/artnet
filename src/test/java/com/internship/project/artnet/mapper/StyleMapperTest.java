package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Style;
import com.internship.project.artnet.model.StyleCreateDTO;
import com.internship.project.artnet.model.StyleDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StyleMapperTest {
    public static final Long ID = 1L;
    public static final String STYLE = "testStyle";

    StyleMapper styleMapper = StyleMapper.INSTANCE;

    @Test
    public void styleToStyleDTO() throws Exception{
        //given
        Style style = new Style(STYLE);
        style.setId(ID);

        //when
        StyleDTO styleDTO = styleMapper.styleToStyleDTO(style);

        //then
        assertEquals(style.getId(), styleDTO.getId());
        assertEquals(style.getStyle(), styleDTO.getStyle());
    }

    @Test
    public void styleDTOToStyle() throws Exception{
        //given
        StyleDTO styleDTO = new StyleDTO(STYLE);
        styleDTO.setId(ID);

        //when
        Style style = styleMapper.styleDTOToStyle(styleDTO);

        //then
        assertEquals(styleDTO.getId(), style.getId());
        assertEquals(styleDTO.getStyle(), style.getStyle());
    }

    @Test
    public void styleCreateDTOToStyle() throws Exception{
        //given
        StyleCreateDTO styleDTO = new StyleCreateDTO(STYLE);

        //when
        Style style = styleMapper.styleDTOToStyle(styleDTO);

        //then
        assertEquals(styleDTO.getStyle(), style.getStyle());
    }




}
