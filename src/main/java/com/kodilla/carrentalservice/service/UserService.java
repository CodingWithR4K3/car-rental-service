package com.kodilla.carrentalservice.service;

import com.kodilla.carrentalservice.dto.UserDto;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.mapper.UserMapper;
import com.kodilla.carrentalservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }

    public UserDto getUserById(final Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    public UserDto getUserByEmail(final String email) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    public UserDto getUserByPhoneNumber(final int phoneNumber) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new));
    }

    public UserDto saveUser(final UserDto userDto) {
        return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }
}
