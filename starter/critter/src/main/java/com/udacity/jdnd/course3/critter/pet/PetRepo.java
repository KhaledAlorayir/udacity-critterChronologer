package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepo extends JpaRepository<Pet,Long> {
    @Query("SELECT p From Pet p JOIN FETCH p.owner o")
    List<Pet> getAllWithOwner();
    List<Pet> findByOwner(Customer customer);
}
