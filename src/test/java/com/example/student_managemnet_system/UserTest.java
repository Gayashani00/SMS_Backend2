package com.example.student_managemnet_system;

import com.example.student_managemnet_system.exception.UserNotFoundException;
import com.example.student_managemnet_system.model.User;
import com.example.student_managemnet_system.repository.UserRepository;
import com.example.student_managemnet_system.controller.UserController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Mock
    private UserRepository userRepository;
//Mock is a method we use as a virtual database
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRegistrationnumber() {
        User user = new User();
        user.setRegistrationnumber("D/BSE/21/0003");
        assertEquals("D/BSE/21/0003", user.getRegistrationnumber());
    }

    @Test
    public void testGetFirstname() {
        User user = new User();
        user.setFirstname("Gayashani");
        assertEquals("Gayashani", user.getFirstname());
    }

    @Test
    public void testGetLastname() {
        User user = new User();
        user.setLastname("Divyanjalee");
        assertEquals("Divyanjalee", user.getLastname());
    }

    @Test
    public void testGetEmail() {
        User user = new User();
        user.setEmail("gayaherath9468@gmail.com");
        assertEquals("gayaherath9468@gmail.com", user.getEmail());
    }

    @Test
    public void testGetId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, user.getId().longValue());
    }


    @Test
    public void testNewUser() {
        User user = new User();
        user.setRegistrationnumber("12345");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userController.newUser(user);

        assertEquals(user.getRegistrationnumber(), savedUser.getRegistrationnumber());
        assertEquals(user.getFirstname(), savedUser.getFirstname());
        assertEquals(user.getLastname(), savedUser.getLastname());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setRegistrationnumber("12345");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User foundUser = userController.getUserById(1L);

        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getRegistrationnumber(), foundUser.getRegistrationnumber());
        assertEquals(user.getFirstname(), foundUser.getFirstname());
        assertEquals(user.getLastname(), foundUser.getLastname());
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userController.getUserById(1L);
        });
    }
}
