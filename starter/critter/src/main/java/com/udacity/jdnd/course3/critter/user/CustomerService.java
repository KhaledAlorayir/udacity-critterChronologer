package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepo;
import com.udacity.jdnd.course3.critter.shared.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final PetRepo petRepo;

    public CustomerDTO save(CustomerDTO customerDTO) {
        return CustomerDTO.MapToCustomerDTO(customerRepo.save(new Customer(customerDTO.getName(),customerDTO.getPhoneNumber(),customerDTO.getNotes())));
    }

    public List<CustomerDTO> getAll() {
        return customerRepo.getAllWithPets().stream().map(CustomerDTO::MapToCustomerDTO).collect(Collectors.toList());
    }

    //TEST THIS
    public CustomerDTO getCustomerByPet(Long petId) {
        Pet pet = petRepo.findById(petId).orElseThrow(() -> new ResourceNotFoundException(Pet.class.getSimpleName()));
        return CustomerDTO.MapToCustomerDTO(pet.getOwner());
    }
}
