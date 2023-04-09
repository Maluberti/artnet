package com.internship.project.artnet.mapper;

import com.internship.project.artnet.model.UsersDTO;
import com.internship.project.artnet.domain.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UsersDTO userToUserDTO(Users user);
    Users userDTOToUser(UsersDTO userDTO);
}
