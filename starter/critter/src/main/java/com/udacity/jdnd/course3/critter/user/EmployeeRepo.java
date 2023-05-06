package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    @Query("SELECT e FROM Employee e JOIN e.schedules sc JOIN FETCH e.skills sk WHERE sk IN :skills AND sc.date = :date")
    List<Employee> findEmployeesBySkillsAndScheduleDate(Set<EmployeeSkill> skills, LocalDate date);
    List<Employee> findByIdIn(List<Long> ids);
}
