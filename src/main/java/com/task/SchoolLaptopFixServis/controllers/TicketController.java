package com.task.SchoolLaptopFixServis.controllers;

import com.task.SchoolLaptopFixServis.enums.Status;
import com.task.SchoolLaptopFixServis.models.Ticket;
import com.task.SchoolLaptopFixServis.requests.CreateTicketRequest;
import com.task.SchoolLaptopFixServis.requests.UpdateTicketRequest;
import com.task.SchoolLaptopFixServis.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping("/{laptopOwner}")
    public ResponseEntity<List<Ticket>> getAllTicketsForCertainLaptopOwner(@PathVariable String laptopOwner){
        return ResponseEntity.ok(
                ticketService.getAllTicketsForACertainLaptopOwner(laptopOwner)
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Ticket> createTicket(@RequestBody CreateTicketRequest request){
        return ResponseEntity.ok(
                ticketService.createTicket(request)
        );
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteTicket(@PathVariable("id") Long id) {
        Boolean isTicketDeleted = ticketService.deleteTicket(id);
        String message = isTicketDeleted.booleanValue() == Boolean.TRUE ? "Deleted ticket with ID "+id+" successfully" : "Couldn't delete ticket, something went wrong";
        return ResponseEntity.ok(
                message
        );
    }


    @PutMapping("/update/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable("ticketId") Long ticketId,
            @RequestBody UpdateTicketRequest request
            ) {
        Ticket updatedTicket = ticketService.updateTicket(ticketId,request);
        return ResponseEntity.ok(updatedTicket);
    }
}
