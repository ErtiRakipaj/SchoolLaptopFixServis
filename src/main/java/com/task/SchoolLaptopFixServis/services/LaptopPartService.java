package com.task.SchoolLaptopFixServis.services;

import com.task.SchoolLaptopFixServis.models.LaptopPart;
import com.task.SchoolLaptopFixServis.repositories.LaptopPartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
                orElseThrow();
        //TODO Add custom Laptop Part not found exception
    }


    public LaptopPart addLaptopPart(LaptopPart part){
        Optional<LaptopPart> partOptional = laptopPartRepository.findLaptopPartByNameIgnoreCase(part.getName());
        if (partOptional.isPresent()){
            throw new IllegalStateException("Part already exists");
            //TODO Add custom part exists exception later
        }

        return laptopPartRepository.save(part);
    }


}
