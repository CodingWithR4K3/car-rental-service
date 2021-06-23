package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.dto.UserDto;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/by_email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return userService.getUserByEmail(email);
    }

    @GetMapping(value = "/by_phone/{number}")
    public UserDto getUserByPhone(@PathVariable int number) throws UserNotFoundException {
        return userService.getUserByPhoneNumber(number);
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }

    @PutMapping
    public void updateUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
