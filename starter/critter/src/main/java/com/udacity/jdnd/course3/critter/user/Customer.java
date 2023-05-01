package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.shared.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Column(unique = true)
    private String phoneNumber;
    @Column(length = 512)
    private String notes;
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

}