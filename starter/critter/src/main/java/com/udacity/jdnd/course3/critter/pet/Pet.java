package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.shared.BaseEntity;
import com.udacity.jdnd.course3.critter.user.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "schedules_pets",
            joinColumns = @JoinColumn(
                    name = "pet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "schedule_id"
            )
    )
    private List<Schedule> schedules;

    public Pet(String name, PetType type, String notes, LocalDate birthDate, Customer owner) {
        super(name);
        this.type = type;
        this.notes = notes;
        this.birthDate = birthDate;
        this.owner = owner;
    }
}