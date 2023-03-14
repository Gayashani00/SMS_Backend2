package com.example.student_managemnet_system.controller;

import com.example.student_managemnet_system.exception.UserNotFoundException;
import com.example.student_managemnet_system.model.User;
import com.example.student_managemnet_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setRegistrationnumber(newUser.getRegistrationnumber());
                    user.setFirstname(newUser.getFirstname());
                    user.setLastname(newUser.getLastname());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }


        @DeleteMapping("/user/{id}")
                String deleteUser(@PathVariable long id){
            if(!userRepository.existsById(id)){
                throw new UserNotFoundException(id);
            }
            userRepository.deleteById(id);
            return "Student with id "+id+ " is deleted successfully!";
        }
    }



