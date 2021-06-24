package com.kodilla.carrentalservice.repository;

import com.kodilla.carrentalservice.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

    @Override
    List<Rental> findAll();
}
