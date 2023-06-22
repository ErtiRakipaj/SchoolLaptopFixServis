package com.task.SchoolLaptopFixServis.services;

import com.task.SchoolLaptopFixServis.models.Laptop;
import com.task.SchoolLaptopFixServis.models.LaptopPart;
import com.task.SchoolLaptopFixServis.repositories.LaptopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LaptopService {
    private final LaptopRepository laptopRepository;

    public List<Laptop> getAllLaptops(){
        return laptopRepository.findAll();
    }

    public Laptop addLaptop(Laptop laptop){
        Optional<Laptop> laptopOptional = laptopRepository.findLaptopByOwner(laptop.getOwner());
        if (laptopOptional.isPresent()){
            throw new RuntimeException("Laptop exists");
        }
        return laptopRepository.save(laptop);
    }
}
