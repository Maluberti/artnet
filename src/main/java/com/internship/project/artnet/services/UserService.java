package com.internship.project.artnet.services;


import com.internship.project.artnet.domain.Users;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();

    Users getUserById(Long id);

    Users createNewUser(Users user);

    Users updateUserById(Long id, Users user);

    Users patchUser(Long id, Users user);

    void deleteUserById(Long id);
}

