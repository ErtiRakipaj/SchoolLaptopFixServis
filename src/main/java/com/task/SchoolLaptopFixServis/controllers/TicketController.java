package com.task.SchoolLaptopFixServis.controllers;

import com.task.SchoolLaptopFixServis.models.Ticket;
import com.task.SchoolLaptopFixServis.requests.CreateTicketRequest;
import com.task.SchoolLaptopFixServis.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptopServisApi/tickets")
@RequiredArgsConstructor
@CrossOrigin
public class TicketController {

    private final TicketService ticketService;


    @GetMapping("")
    public ResponseEntity<List<Ticket>> getAllTickets(){
        return ResponseEntity.ok(
                ticketService.getAllTickets()
        );
    }


    @PostMapping("/add")
    public ResponseEntity<Ticket> createTicket(@RequestBody CreateTicketRequest request){
        return ResponseEntity.ok(
                ticketService.createTicket(request)
        );
    }
}
