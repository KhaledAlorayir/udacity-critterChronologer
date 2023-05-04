package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.shared.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepo petRepo;
    private final CustomerRepo customerRepo;

    public PetDTO save(PetDTO petDTO) {
        Customer customer = customerRepo.findById(petDTO.getOwnerId()).orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName()));
        return PetDTO.mapToPetDTO(petRepo.save(new Pet(petDTO.getName(),petDTO.getType(),petDTO.getNotes(),petDTO.getBirthDate(),customer)));
    }

    public List<PetDTO> getAll() {
        return petRepo.getAllWithOwner().stream().map(PetDTO::mapToPetDTO).collect(Collectors.toList());
    }

    public PetDTO findById(Long id) {
        return PetDTO.mapToPetDTO(petRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Pet.class.getSimpleName())));
    }

    public List<PetDTO> getPetsByOwner(Long ownerId) {
        Customer customer = customerRepo.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName()));
        return petRepo.findByOwner(customer).stream().map(pet -> PetDTO.mapToPetDTO(pet,customer)).collect(Collectors.toList());
    }
}
