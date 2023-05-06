package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleDAO {

    private final ScheduleRepo scheduleRepo;

    public List<Schedule> getAll() {
        List<Schedule> results = scheduleRepo.getAllWithEmployees();
        results = scheduleRepo.getAllWithPets();
        return  results;
    }

    public List<Schedule> getAllByPet(Pet pet) {
        List<Schedule> results = scheduleRepo.getWithPetsByPet(pet);
        results = scheduleRepo.getWithEmployeesBySchedules(results);
        return  results;
    }

    public List<Schedule> getAllByEmployee(Employee employee) {
        List<Schedule> results = scheduleRepo.getWithEmployeesByEmployee(employee);
        results = scheduleRepo.getWithPetsBySchedules(results);
        return  results;
    }

    public List<Schedule> getAllByCustomer(Customer customer) {
        List<Schedule> results = scheduleRepo.getWithPetsByCustomer(customer);
        results = scheduleRepo.getWithEmployeesBySchedules(results);
        return  results;
    }

}
