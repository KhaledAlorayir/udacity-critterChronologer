package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "schedules_employees",
            joinColumns = @JoinColumn(
                    name = "schedule_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "employee_id"
            )
    )
    private List<Employee> employees;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "schedules_pets",
            joinColumns = @JoinColumn(
                    name = "schedule_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "pet_id"
            )
    )    private List<Pet> pets;
    private LocalDate date;

    public Schedule(List<Employee> employees, List<Pet> pets, LocalDate date) {
        this.employees = employees;
        this.pets = pets;
        this.date = date;
    }
}
