package com.kodilla.carrentalservice.repository;

import com.kodilla.carrentalservice.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(int phoneNumber);

    Boolean existsByEmail(String email);
}
