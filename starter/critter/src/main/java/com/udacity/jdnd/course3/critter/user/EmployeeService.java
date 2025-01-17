package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.shared.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        return EmployeeDTO.mapToEmployeeDTO(employeeRepo.save(new Employee(employeeDTO.getName(),employeeDTO.getSkills(),employeeDTO.getDaysAvailable())));
    }

    public EmployeeDTO findById(Long id) {
        return EmployeeDTO.mapToEmployeeDTO(employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName())));
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        employeeRepo.findById(employeeId).map((employee) -> {
            employee.setDaysAvailable(daysAvailable);
            return employeeRepo.save(employee);
        }).orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName()));
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        return employeeRepo.findEmployeesBySkillsAndDaysAvailable(employeeDTO.getDate().getDayOfWeek()).stream().filter((user) -> user.getSkills().containsAll(employeeDTO.getSkills())).map(EmployeeDTO::mapToEmployeeDTO).collect(Collectors.toList());
    }
}
