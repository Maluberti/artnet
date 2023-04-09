package com.internship.project.artnet.services;


import com.internship.project.artnet.controllers.UserController;
import com.internship.project.artnet.domain.Users;
import com.internship.project.artnet.mapper.UserMapper;
import com.internship.project.artnet.model.UserListDTO;
import com.internship.project.artnet.model.UsersDTO;
import com.internship.project.artnet.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final UsersRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UsersRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        List<UsersDTO> userDTOS =
                userRepository.findAll()
                        .stream()
                        .map(user -> {
                            UsersDTO userDTO = userMapper.userToUserDTO(user);
                            userDTO.setUserUrl(getUserUrl(user.getId()));
                            return userDTO;
                        })
                        .collect(Collectors.toList());

        return userDTOS;
    }

    @Override
    public UsersDTO getUserById(Long id) {

        return userRepository.findById(id)
                .map(userMapper::userToUserDTO)
                .map(userDTO -> {
                    //set API URL
                    userDTO.setUserUrl(getUserUrl(id));
                    return userDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UsersDTO createNewUser(UsersDTO userDTO) {

        return saveAndReturnDTO(userMapper.userDTOToUser(userDTO));
    }

    private UsersDTO saveAndReturnDTO(Users user) {
        Users savedUser = userRepository.save(user);

        UsersDTO returnDto = userMapper.userToUserDTO(savedUser);

        returnDto.setUserUrl(getUserUrl(savedUser.getId()));

        return returnDto;
    }

    @Override
    public UsersDTO saveUserByDTO(Long id, UsersDTO userDTO) {
        Users user = userMapper.userDTOToUser(userDTO);
        user.setId(id);

        return saveAndReturnDTO(user);
    }

    @Override
    public UsersDTO patchUser(Long id, UsersDTO userDTO) {
        return userRepository.findById(id).map(user -> {

            if(userDTO.getName() != null){
                user.setName(userDTO.getName());
            }

            if(userDTO.getEmail() != null){
                user.setEmail(userDTO.getEmail());
            }

            if(userDTO.getPassword() != null){
                user.setPassword(userDTO.getPassword());
            }

            if(userDTO.getIsArtist() != null){
                user.setIsArtist(userDTO.getIsArtist());
            }
            if(userDTO.getIsAdmirer() != null){
                user.setIsAdmirer(userDTO.getIsAdmirer());
            }

            UsersDTO returnDto = userMapper.userToUserDTO(userRepository.save(user));

            returnDto.setUserUrl(getUserUrl(id));

            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    private String getUserUrl(Long id) {
        return UserController.BASE_URL + "/" + id;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}

