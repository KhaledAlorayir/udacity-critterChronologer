package com.udacity.jdnd.course3.critter.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;

    public static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(),employee.getName(),employee.getSkills(),employee.getDaysAvailable());
    }
}
