package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.shared.BaseEntity;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;


    public static ScheduleDTO mapToScheduleDTO(Schedule schedule) {
        List<Long> employeeIds = schedule.getEmployees().stream().map(BaseEntity::getId).collect(Collectors.toList());
        List<Long> petIds = schedule.getPets().stream().map(BaseEntity::getId).collect(Collectors.toList());
        Set<EmployeeSkill> activities = schedule.getEmployees().stream().map(Employee::getSkills).flatMap(Set::stream).collect(Collectors.toSet());
        return new ScheduleDTO(schedule.getId(),employeeIds,petIds,schedule.getDate(),activities);
    }
}
