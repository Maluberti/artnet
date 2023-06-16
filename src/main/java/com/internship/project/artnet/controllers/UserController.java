package com.internship.project.artnet.controllers;

import com.internship.project.artnet.domain.Users;
import com.internship.project.artnet.mapper.UserMapper;
import com.internship.project.artnet.model.UserCreateDTO;
import com.internship.project.artnet.model.UserListDTO;
import com.internship.project.artnet.model.UsersDTO;
import com.internship.project.artnet.services.AdmirerService;
import com.internship.project.artnet.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(UserController.BASE_URL)
@Api(value = "User")
public class UserController {
    public static final String BASE_URL = "/users";

    private final UserService userService;
    private final AdmirerService admirerService;
    private final UserMapper userMapper;

    public UserController(UserService userService, AdmirerService admirerService, UserMapper userMapper) {
        this.userService = userService;
        this.admirerService = admirerService;
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "Return a list of Users")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListDTO getListOfUsers(){
        return new  UserListDTO( userService.getAllUsers()
        .stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));

    }

    @ApiOperation(value = "Return a User by its id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UsersDTO getUserById(@PathVariable Long id){
        return toDTO(userService.getUserById(id));
    }


    @ApiOperation(value = "Create new User")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersDTO createNewUser(@RequestBody UserCreateDTO userDTO){

        return toDTO(userService.createNewUser(toUsers(userDTO)));
    }

    @ApiOperation(value = "Update User")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UsersDTO updateUser(@PathVariable Long id, @RequestBody UserCreateDTO userDTO){
        Users user = toUsers(userDTO);
        return toDTO(userService.updateUserById(id, user));
    }

    @ApiOperation(value = "Patch User")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UsersDTO patchUser(@PathVariable Long id, @RequestBody UserCreateDTO userDTO){

        return toDTO(userService.patchUser(id, toUsers(userDTO)));
    }

    @ApiOperation(value = "Delete User")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    // aux

    private UsersDTO toDTO (Users user){
        UsersDTO userDTO = userMapper.userToUserDTO(user);
        userDTO.setUserUrl(getUserUrl(user.getId()));
        return userDTO;
    }

    private Users toUsers (UsersDTO userDTO){
        return userMapper.userDTOToUser(userDTO);
    }

    private Users toUsers (UserCreateDTO userDTO){
        return userMapper.userDTOToUser(userDTO);
    }

    private String getUserUrl(Long id) {
        return UserController.BASE_URL + "/" + id;
    }
}
