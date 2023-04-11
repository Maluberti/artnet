package com.internship.project.artnet.mapper;

import com.internship.project.artnet.domain.Users;
import com.internship.project.artnet.model.UsersDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;



public class UserMapperTest {
    public static final String NAME = "testName";
    public static final String EMAIL = "testName";
    public static final String PASSWORD = "testName";
    public static final Boolean IS_ARTIST = true;
    public static final Boolean IS_ADMIRER = false;


    UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void userToUserDTO() throws Exception {
        //given
        Users user = new Users(7l, NAME, EMAIL, PASSWORD, IS_ARTIST,IS_ADMIRER);

        //when
        UsersDTO userDTO = userMapper.userToUserDTO(user);

        //then
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getIsArtist(), userDTO.getIsArtist());
        assertEquals(user.getIsAdmirer(), userDTO.getIsAdmirer());
    }

    @Test
    public void userDTOtoUser() throws Exception {
        //given
        UsersDTO userDTO = new UsersDTO(1l, NAME, EMAIL,PASSWORD,IS_ARTIST,IS_ADMIRER);

        //when
        Users user = userMapper.userDTOToUser(userDTO);

        //then
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.getIsArtist(), user.getIsArtist());
        assertEquals(userDTO.getIsAdmirer(), user.getIsAdmirer());
    }
}
