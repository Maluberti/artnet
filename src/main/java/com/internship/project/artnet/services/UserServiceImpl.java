package com.internship.project.artnet.services;


import com.internship.project.artnet.domain.Users;
import com.internship.project.artnet.mapper.UserMapper;
import com.internship.project.artnet.model.UsersDTO;
import com.internship.project.artnet.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UsersRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found!"));

    }
    @Override
    public Users createNewUser(Users user) {
        return userRepository.save(user);

    }

    @Override
    public Users updateUserById(Long id, Users user) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found!"));
        user.setId(id);

        return userRepository.save(user);
    }

    @Override
    public Users patchUser(Long id, Users user) {
        return userRepository.findById(id).map(savedUser -> {

            if(user.getName() != null){
                savedUser.setName(user.getName());
            }

            if(user.getEmail() != null){
                savedUser.setEmail(user.getEmail());
            }

            if(user.getPassword() != null){
                savedUser.setPassword(user.getPassword());
            }

            if(user.getIsArtist() != null){
                savedUser.setIsArtist(user.getIsArtist());
            }
            if(user.getIsAdmirer() != null){
                savedUser.setIsAdmirer(user.getIsAdmirer());
            }

            return userRepository.save(savedUser);

        }).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found!"));
    }



    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}

