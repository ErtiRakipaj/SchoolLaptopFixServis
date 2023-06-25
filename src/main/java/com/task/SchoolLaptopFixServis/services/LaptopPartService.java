package com.task.SchoolLaptopFixServis.services;

import com.task.SchoolLaptopFixServis.models.LaptopPart;
import com.task.SchoolLaptopFixServis.repositories.LaptopPartRepository;
import com.task.SchoolLaptopFixServis.requests.UpdateLaptopPartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
public class LaptopPartService {
    private final LaptopPartRepository laptopPartRepository;


    public List<LaptopPart> getAllLaptopParts(){
        return laptopPartRepository.findAll();
    }


    public LaptopPart getLaptopPartById(Long id) {
        return laptopPartRepository.findLaptopPartById(id).
                orElseThrow(
                        ()->new RuntimeException("Laptop Part with ID "+id+" not found")
                );
    }


    public LaptopPart addLaptopPart(LaptopPart part){
        Optional<LaptopPart> partOptional = laptopPartRepository.findLaptopPartByNameIgnoreCase(part.getName());
        if (partOptional.isPresent()){
            throw new IllegalStateException("Part already exists");
        }

        return laptopPartRepository.save(part);
    }



    public Boolean deleteLaptopPart(Long id) {
        if (laptopPartRepository.findLaptopPartById(id).isEmpty()) {
            throw new RuntimeException("Laptop Part not found");
        }
        laptopPartRepository.deleteLaptopPartById(id);
        return TRUE;
    }

    public LaptopPart updateLaptopPart(Long id, UpdateLaptopPartRequest updatePartRequest) {
        LaptopPart existingLaptopPart = laptopPartRepository.findLaptopPartById(id)
                .orElseThrow(
                        ()->new RuntimeException("Invalid Laptop Part ID")
                );

        existingLaptopPart.setStock(updatePartRequest.getStock());
        existingLaptopPart.setName(updatePartRequest.getName());
        existingLaptopPart.setDescription(updatePartRequest.getDescription());
        existingLaptopPart.setPrice(updatePartRequest.getPrice());

        return laptopPartRepository.save(existingLaptopPart);
    }

}
