package com.kodilla.carrentalservice.service;

import com.kodilla.carrentalservice.domain.User;
import com.kodilla.carrentalservice.dto.UserDto;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.mapper.UserMapper;
import com.kodilla.carrentalservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserDto getUserById(Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    public UserDto getUserByPhoneNumber(int phoneNumber) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new));
    }

    public User saveUser(final UserDto userDto) {
        return userRepository.save(userMapper.mapToUser(userDto));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
