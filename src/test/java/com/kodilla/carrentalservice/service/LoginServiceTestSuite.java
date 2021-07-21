package com.kodilla.carrentalservice.service;

import com.kodilla.carrentalservice.domain.Login;
import com.kodilla.carrentalservice.exception.LoginNotFoundException;
import com.kodilla.carrentalservice.repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class LoginServiceTestSuite {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private LoginRepository loginRepository;

    @Test
    public void isLoginRegisteredTest() {
        //Given
        when(loginRepository.existsByEmailAndPassword("email", "password")).thenReturn(true);

        //When
        Boolean result = loginService.isLoginRegistered("email", "password");

        assertTrue(result);
    }

    @Test
    public void getLoginByEmailAndPasswordTest() throws LoginNotFoundException {
        //Given
        Login login = initLogin();
        when(loginRepository.findByEmailAndPassword("email", "password")).thenReturn(Optional.of(login));

        //When
        Login result = loginService.getLoginByEmailAndPassword("email", "password");

        //Then
        assertEquals("email", result.getEmail());
        assertEquals("password", result.getPassword());
    }

    @Test
    public void saveLoginTest() {
        //Given
        Login login = initLogin();

        //When
        loginService.saveLogin(login);

        //Then
        verify(loginRepository, times(1)).save(login);
    }

    @Test
    public void deleteLoginTest() {
        //Given
        Login login = initLogin();

        //When
        loginService.deleteLogin(login);

        //Then
        verify(loginRepository, times(1)).delete(login);
    }

    private Login initLogin() {
        return new Login("email", "password");
    }
}
