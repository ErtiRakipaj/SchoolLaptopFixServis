package com.task.SchoolLaptopFixServis.controllers;

import com.task.SchoolLaptopFixServis.models.Laptop;
import com.task.SchoolLaptopFixServis.models.LaptopPart;
import com.task.SchoolLaptopFixServis.services.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptopServisApi/laptops")
@RequiredArgsConstructor
@CrossOrigin
public class LaptopController {

    private final LaptopService laptopService;

    @GetMapping("")
    public ResponseEntity<List<Laptop>> getAllLaptops(){
        return ResponseEntity.ok(
                laptopService.getAllLaptops()
        );
    }


    @PostMapping("/add")
    public ResponseEntity<Laptop> createLaptopPart(@RequestBody Laptop laptop){
        return ResponseEntity.ok(
                laptopService.addLaptop(laptop)
        );
    }
}
