package com.kodilla.carrentalservice.facade;

import com.kodilla.carrentalservice.dto.UserDto;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.mapper.UserMapper;
import com.kodilla.carrentalservice.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserFacade(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    public UserDto getUserById(final Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserById(id));
    }

    public UserDto getUserByEmail(final String email) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByEmail(email));
    }

    public UserDto getUserByPhoneNumber(final int phoneNumber) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByPhoneNumber(phoneNumber));
    }

    public UserDto saveUser(final UserDto userDto) {
        return userMapper.mapToUserDto(userService.saveUser(userDto));
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public Boolean isUserAlreadyRegistered(String email) {
        return userService.isUserAlreadyRegistered(email);
    }
}
