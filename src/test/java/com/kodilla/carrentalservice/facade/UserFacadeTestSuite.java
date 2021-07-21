package com.kodilla.carrentalservice.facade;


import com.kodilla.carrentalservice.domain.Login;
import com.kodilla.carrentalservice.domain.User;
import com.kodilla.carrentalservice.dto.UserDto;
import com.kodilla.carrentalservice.dto.api.email.EmailVerificationDto;
import com.kodilla.carrentalservice.exception.LoginNotFoundException;
import com.kodilla.carrentalservice.exception.UserNotFoundException;
import com.kodilla.carrentalservice.mapper.UserMapper;
import com.kodilla.carrentalservice.service.LoginService;
import com.kodilla.carrentalservice.service.UserService;
import com.kodilla.carrentalservice.service.email.EmailVerificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserFacadeTestSuite {

    @InjectMocks
    private UserFacade userFacade;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserService userService;

    @Mock
    private LoginService loginService;

    @Mock
    private EmailVerificationService emailVerificationService;

    @Test
    public void userSaveTest() {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();
        EmailVerificationDto emailVerificationDto = initEmailVerificationDto();

        when(emailVerificationService.verifyEmail(any())).thenReturn(emailVerificationDto);
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.saveUser(userDto)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        doNothing().when(loginService).saveLogin(ArgumentMatchers.any());

        //When
        UserDto savedUser = userFacade.saveUser(userDto);

        //Then
        assertEquals(userDto.getId(), savedUser.getId());
        assertEquals(userDto.getName(), savedUser.getName());
    }

    @Test
    public void getUserByIdTest() throws UserNotFoundException {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();

        when(userService.getUserById(1L)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto result = userFacade.getUserById(1L);

        //Then
        assertEquals(userDto.getId(), result.getId());
    }

    @Test
    public void getUserByEmailTest() throws UserNotFoundException {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();

        when(userService.getUserByEmail("email")).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto result = userFacade.getUserByEmail("email");

        //Then
        assertEquals(userDto.getEmail(), result.getEmail());
    }

    @Test
    public void getUserByPhoneNumberTest() throws UserNotFoundException {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();

        when(userService.getUserByPhoneNumber(123456)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto result = userFacade.getUserByPhoneNumber(123456);

        //Then
        assertEquals(userDto.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    public void getUsersTest() {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();

        List<User> userList = Collections.singletonList(user);
        List<UserDto> userDtoList = Collections.singletonList(userDto);

        when(userService.getAllUsers()).thenReturn(userList);
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);

        //When
        List<UserDto> resultList = userFacade.getAllUsers();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(u -> {
            assertEquals(u.getId(), userDto.getId());
            assertEquals(u.getName(), userDto.getName());
            assertEquals(u.getPhoneNumber(), userDto.getPhoneNumber());
            assertEquals(u.getPassword(), userDto.getPassword());
        });
    }

    @Test
    public void deleteUserTest() throws LoginNotFoundException, UserNotFoundException {
        //Given
        User user = initUser();
        Login login = initLogin();

        when(userService.getUserById(anyLong())).thenReturn(user);
        when(loginService.getLoginByEmailAndPassword(anyString(), anyString())).thenReturn(login);
        doNothing().when(loginService).deleteLogin(any());

        //When
        userFacade.deleteUser(2L);

        //Then
        verify(userService, times(1)).deleteUser(2L);
    }

    @Test
    public void isUserAlreadyRegisteredTest() {
        //Given
        when(userService.isUserAlreadyRegistered("email")).thenReturn(true);

        //When
        boolean result = userFacade.isUserAlreadyRegistered("email");

        assertTrue(result);
    }
    
    private User initUser() {
        return new User(
                1L,
                "Jack",
                "Smith",
                "email",
                "password",
                123456);
    }

    private UserDto initUserDto() {
        return new UserDto(
                1L,
                "Jack",
                "Smith",
                "email",
                "password",
                123456);
    }

    private EmailVerificationDto initEmailVerificationDto() {
        return new EmailVerificationDto("true", "true", "true");
    }

    private Login initLogin() {
        return new Login(
                "email",
                "password"
        );
    }
}
