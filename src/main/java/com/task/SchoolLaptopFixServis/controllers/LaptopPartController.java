package com.task.SchoolLaptopFixServis.controllers;

import com.task.SchoolLaptopFixServis.models.LaptopPart;
import com.task.SchoolLaptopFixServis.models.Ticket;
import com.task.SchoolLaptopFixServis.requests.UpdateLaptopPartRequest;
import com.task.SchoolLaptopFixServis.requests.UpdateTicketRequest;
import com.task.SchoolLaptopFixServis.services.LaptopPartService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteLaptopPart(@PathVariable Long id) {
        Boolean isPartDeleted = laptopPartService.deleteLaptopPart(id);
        String message = isPartDeleted.booleanValue() == Boolean.TRUE ? "Deleted part with ID "+id+" successfully" : "Couldn't delete part, something went wrong";

        return ResponseEntity.ok(
                message
        );
    }

    @PutMapping("/update/{partId}")
    public ResponseEntity<LaptopPart> updateLaptopPart(
            @PathVariable("partId") Long partId,
            @RequestBody UpdateLaptopPartRequest request
    ) {
        LaptopPart updatedPart = laptopPartService.updateLaptopPart(partId,request);
        return ResponseEntity.ok(updatedPart);
    }
}
