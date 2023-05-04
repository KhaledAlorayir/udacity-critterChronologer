package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private Long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

    public static PetDTO mapToPetDTO(Pet pet) {
        return new PetDTO(pet.getId(),pet.getType(),pet.getName(),pet.getOwner().getId(),pet.getBirthDate(),pet.getNotes());
    }

    public static PetDTO mapToPetDTO(Pet pet, Customer customer) {
        return new PetDTO(pet.getId(),pet.getType(),pet.getName(),customer.getId(),pet.getBirthDate(),pet.getNotes());
    }
}
