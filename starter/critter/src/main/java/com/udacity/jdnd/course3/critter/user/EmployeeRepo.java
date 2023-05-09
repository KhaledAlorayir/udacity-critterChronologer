package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.daysAvailable dv WHERE :day member of dv")
    List<Employee> findEmployeesBySkillsAndDaysAvailable(DayOfWeek day);
    List<Employee> findByIdIn(List<Long> ids);
}
