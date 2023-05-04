package com.udacity.jdnd.course3.critter.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

    public static CustomerDTO MapToCustomerDTO(Customer customer){
        List<Long> petsId;

        if(customer.getPets() != null) {
            petsId = customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList());
        } else {
            petsId = new ArrayList<>();
        }
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getPhoneNumber(),customer.getNotes(),petsId);
    }

}
