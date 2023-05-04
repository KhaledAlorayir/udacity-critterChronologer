package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.shared.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Pet> pets;

    public Customer(String name, String phoneNumber, String notes) {
        super(name);
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }
}