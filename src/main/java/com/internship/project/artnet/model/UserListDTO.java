package com.internship.project.artnet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserListDTO {
    List<UsersDTO> users = null;

    public UserListDTO(List<UsersDTO> users) {
        this.users = users;
    }
}

