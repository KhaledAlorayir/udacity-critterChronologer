package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.shared.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.shared.UtilService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;
    private final PetRepo petRepo;
    private final EmployeeRepo employeeRepo;
    private final UtilService utilService;
    private final ScheduleDAO scheduleDAO;
    private final CustomerRepo customerRepo;

    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        List<Pet> pets = petRepo.findByIdIn(scheduleDTO.getPetIds());
        List<Employee> employees = employeeRepo.findByIdIn(scheduleDTO.getEmployeeIds());

        utilService.validateIdsExist(scheduleDTO.getEmployeeIds(), employees);
        utilService.validateIdsExist(scheduleDTO.getPetIds(), pets);

        return ScheduleDTO.mapToScheduleDTO(scheduleRepo.save(new Schedule(employees, pets,scheduleDTO.getDate())));
    }

    public List<ScheduleDTO> getAll() {
        return scheduleDAO.getAll().stream().map(ScheduleDTO::mapToScheduleDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForPet(Long petId) {
        Pet pet = petRepo.findById(petId).orElseThrow(() -> new ResourceNotFoundException("pet"));
        return scheduleDAO.getAllByPet(pet).stream().map(ScheduleDTO::mapToScheduleDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForEmployee(Long empId) {
        Employee employee = employeeRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("employee"));
        return scheduleDAO.getAllByEmployee(employee).stream().map(ScheduleDTO::mapToScheduleDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForCustomer(Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer"));
        return scheduleDAO.getAllByCustomer(customer).stream().map(ScheduleDTO::mapToScheduleDTO).collect(Collectors.toList());
    }

}
