package com.internship.project.artnet.services;


import com.internship.project.artnet.model.UsersDTO;

import java.util.List;

public interface UserService {
    List<UsersDTO> getAllUsers();

    UsersDTO getUserById(Long id);

    UsersDTO createNewUser(UsersDTO userDTO);

    UsersDTO saveUserByDTO(Long id, UsersDTO userDTO);

    UsersDTO patchUser(Long id, UsersDTO userDTO);

    void deleteUserById(Long id);
}

