package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule,Long> {
    @Query("SELECT DISTINCT s from Schedule s JOIN FETCH s.pets")
    List<Schedule> getAllWithPets();
    @Query("SELECT DISTINCT s from Schedule s JOIN FETCH s.employees")
    List<Schedule> getAllWithEmployees();
    @Query("SELECT DISTINCT s from Schedule s JOIN FETCH s.pets WHERE :pet member of s.pets")
    List<Schedule> getWithPetsByPet(Pet pet);
    @Query("SELECT DISTINCT s from Schedule s JOIN FETCH s.employees WHERE s IN :schedules")
    List<Schedule> getWithEmployeesBySchedules(List<Schedule> schedules);
    @Query("SELECT DISTINCT s from Schedule s JOIN FETCH s.employees WHERE :employee member of s.employees")
    List<Schedule> getWithEmployeesByEmployee(Employee employee);
    @Query("SELECT DISTINCT s from Schedule s JOIN FETCH s.pets WHERE s IN :schedules")
    List<Schedule> getWithPetsBySchedules(List<Schedule> schedules);
    @Query("SELECT DISTINCT s from Schedule s JOIN FETCH s.pets p JOIN p.owner o WHERE o = :customer")
    List<Schedule> getWithPetsByCustomer(Customer customer);

}
