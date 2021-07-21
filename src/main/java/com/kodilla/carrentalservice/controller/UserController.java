package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.dto.UserDto;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.facade.UserFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userFacade.getUserById(id);
    }

    @GetMapping("/by_email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return userFacade.getUserByEmail(email);
    }

    @GetMapping("/by_phone/{number}")
    public UserDto getUserByPhone(@PathVariable int number) throws UserNotFoundException {
        return userFacade.getUserByPhoneNumber(number);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userFacade.saveUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userFacade.saveUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
    }

    @GetMapping("/is_user_registered")
    public Boolean isUserAlreadyRegistered(@RequestParam String email) {
        return userFacade.isUserAlreadyRegistered(email);
    }
}
