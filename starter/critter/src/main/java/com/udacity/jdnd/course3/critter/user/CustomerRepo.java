package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    @Query("SELECT DISTINCT c FROM Customer c LEFT JOIN FETCH c.pets")
    List<Customer> getAllWithPets();
}
