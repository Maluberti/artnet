package com.internship.project.artnet.controllers;

import com.internship.project.artnet.model.UserListDTO;
import com.internship.project.artnet.model.UsersDTO;
import com.internship.project.artnet.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
    public static final String BASE_URL = "/users";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListDTO getListOfUsers(){
        return new UserListDTO(userService.getAllUsers());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UsersDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersDTO createNewUser(@RequestBody UsersDTO userDTO){
        return userService.createNewUser(userDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UsersDTO updateUser(@PathVariable Long id, @RequestBody UsersDTO userDTO){
        return userService.saveUserByDTO(id, userDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UsersDTO patchUser(@PathVariable Long id, @RequestBody UsersDTO userDTO){
        return userService.patchUser(id, userDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
