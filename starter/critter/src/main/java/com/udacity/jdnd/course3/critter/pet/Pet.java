package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.shared.BaseEntity;
import com.udacity.jdnd.course3.critter.user.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Pet extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PetType type;
    @Column(length = 512)
    private String notes;
    @CreationTimestamp
    private LocalDate birthDate;
    @ManyToOne(optional = false)
    private Customer owner;

    @ManyToMany
    private List<Schedule> schedules;
}