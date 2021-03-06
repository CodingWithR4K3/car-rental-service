package com.kodilla.carrentalservice.service;

import com.kodilla.carrentalservice.domain.Login;
import com.kodilla.carrentalservice.exception.LoginNotFoundException;
import com.kodilla.carrentalservice.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Boolean isLoginRegistered(String email, String password) {
        return loginRepository.existsByEmailAndPassword(email, password);
    }

    public Login getLoginByEmailAndPassword(String email, String password) throws LoginNotFoundException {
        return loginRepository.findByEmailAndPassword(email, password).orElseThrow(LoginNotFoundException::new);
    }

    public void saveLogin(Login login) {
        loginRepository.save(login);
    }

    public void deleteLogin(Login login) {
        loginRepository.delete(login);
    }
}
