package com.task.SchoolLaptopFixServis.controllers;

import com.task.SchoolLaptopFixServis.models.LaptopPart;
import com.task.SchoolLaptopFixServis.services.LaptopPartService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptopServisApi/parts")
@RequiredArgsConstructor
@CrossOrigin
public class LaptopPartController {

    private final LaptopPartService laptopPartService;

    @GetMapping("")
    public ResponseEntity<List<LaptopPart>> getAllParts(){
        return ResponseEntity.ok(
            laptopPartService.getAllLaptopParts()
        );
    }


    @PostMapping("/add")
    public ResponseEntity<LaptopPart> createLaptopPart(@RequestBody LaptopPart laptopPart){
        return ResponseEntity.ok(
            laptopPartService.addLaptopPart(laptopPart)
        );
    }
}
