package com.internship.project.artnet.services;

import com.internship.project.artnet.domain.Users;
import com.internship.project.artnet.mapper.UserMapper;
import com.internship.project.artnet.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserMapper userMapper;

    @Mock
    private UsersRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers() {
        List<Users> users = new ArrayList<>();
        users.add(new Users("John", "john@example.com", "password", true, false));
        users.add(new Users("Jane", "jane@example.com", "password", false, true));
        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<Users> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("jane@example.com", result.get(1).getEmail());
    }

    @Test
    public void testGetUserById() {
        Users user = new Users("John", "john@example.com", "password", true, false);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Users result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    public void testGetUserByIdNotFoundException() {
        // Set up the mock repository to return an empty optional
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call getUserById() with an invalid ID and assert that it throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1234L));
    }

    @Test
    public void testCreateNewUser() {
        Users user = new Users("John", "john@example.com", "password", true, false);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        Users result = userService.createNewUser(user);

        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    public void testUpdateUserById() {
        Users user = new Users("John", "john@example.com", "password", true, false);
        user.setId(1L);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));


        Users updatedUser = new Users("John Smith", "john.smith@example.com", "newpassword", true, false);
        updatedUser.setId(1L);

        Mockito.when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        Users result = userService.updateUserById(user.getId(), updatedUser);

        assertNotNull(result);
        assertEquals("John Smith", result.getName());
        assertEquals("john.smith@example.com", result.getEmail());
        assertEquals("newpassword", result.getPassword());
    }

    @Test
    public void testUpdateUserByIdNotFound() {
        // Given
        Users updatedUser = new Users(1L, "Jane", "jane@example.com", "password", false, true);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUserById(1L, updatedUser);
        });

        // Then
        assertEquals("User 1 not found!", exception.getMessage());
    }

    @Test
    public void testPatchUser() {
        Users user = new Users("John", "john@example.com", "password", true, false);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        Users updatedUser = new Users();
        updatedUser.setName("John Smith");
        updatedUser.setEmail("john.smith@example.com");

        Users result = userService.patchUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("John Smith", result.getName());
        assertEquals("john.smith@example.com", result.getEmail());
        assertEquals("password", result.getPassword());
        assertTrue(result.getIsArtist());
        assertFalse(result.getIsAdmirer());
    }

    @Test
    void testPatchUserWithInvalidId() {
        Long id = 1L;
        Users user = new Users("John Doe", "johndoe@example.com", "password", true, false);

        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.patchUser(id, user);
        });

        assertEquals("User " + id + " not found!", exception.getMessage());
    }

    @Test
    void testDeleteUserById() {
        Long id = 1L;

        userService.deleteUserById(id);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(id);
    }
}
