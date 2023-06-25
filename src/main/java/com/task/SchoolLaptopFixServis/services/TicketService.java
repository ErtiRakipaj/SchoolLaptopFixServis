package com.task.SchoolLaptopFixServis.services;

import com.task.SchoolLaptopFixServis.enums.Status;
import com.task.SchoolLaptopFixServis.models.Laptop;
import com.task.SchoolLaptopFixServis.models.LaptopPart;
import com.task.SchoolLaptopFixServis.models.Ticket;
import com.task.SchoolLaptopFixServis.repositories.LaptopPartRepository;
import com.task.SchoolLaptopFixServis.repositories.LaptopRepository;
import com.task.SchoolLaptopFixServis.repositories.TicketRepository;
import com.task.SchoolLaptopFixServis.requests.CreateTicketRequest;
import com.task.SchoolLaptopFixServis.requests.UpdateTicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final LaptopRepository laptopRepository;
    private final LaptopPartRepository partRepository;


    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllTicketsForACertainLaptopOwner(String laptopOwner) {
        return ticketRepository.findTicketsByLaptop_OwnerIgnoreCase(laptopOwner);
    }


    public Ticket createTicket(CreateTicketRequest ticketRequest) {
        if (ticketRepository.findTicketByLaptopIdAndTicketStatus(ticketRequest.getLaptopId(), Status.OPEN).isPresent()) {
            throw new RuntimeException("There already is an open ticket for this laptop");
        }
        Laptop laptop = laptopRepository.findById(ticketRequest.getLaptopId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid laptop ID"));

        List<LaptopPart> laptopParts = partRepository.findAllByNameIgnoreCaseIn(ticketRequest.getPartNames());
        if (laptopParts.size() != ticketRequest.getPartNames().size()) {
            throw new RuntimeException("One or more parts do not exist in our inventory");
        }


        Ticket ticket = Ticket.builder()
                .laptop(laptop)
                .description(ticketRequest.getDescription())
                .ticketStatus(Status.OPEN)
                .build();

        for (LaptopPart part : laptopParts) {
            if (part.getStock() <= 0) {
                throw new RuntimeException("Part " + part.getName() + " is out of stock");
            }
            part.setStock(part.getStock() - 1);
            List<Ticket> tickets = new ArrayList<>();
            tickets.add(ticket);
            part.setTickets(tickets);
        }

        ticket.setLaptopParts(laptopParts);

        return ticketRepository.save(ticket);
    }


    public Boolean deleteTicket(Long id){
        if (ticketRepository.findTicketById(id).isEmpty()) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepository.deleteTicketById(id);
        return TRUE;
    }

    public Ticket updateTicket(Long ticketId, UpdateTicketRequest updateRequest) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID"));


        existingTicket.setDescription(updateRequest.getDescription());
        existingTicket.setTicketStatus(updateRequest.getTicketStatus());


        List<LaptopPart> laptopParts = partRepository.findAllByNameIgnoreCaseIn(updateRequest.getLaptopParts());
        if (laptopParts.size() != updateRequest.getLaptopParts().size()) {
            throw new RuntimeException("One or more parts do not exist in our inventory");
        }

        for (LaptopPart part : existingTicket.getLaptopParts()) {
            part.getTickets().remove(existingTicket);
        }

        existingTicket.getLaptopParts().clear();

        for (LaptopPart part : laptopParts) {

            if (part.getStock() <= 0) {
                throw new RuntimeException("Part " + part.getName() + " is out of stock");
            }


            part.getTickets().add(existingTicket);

            existingTicket.getLaptopParts().add(part);

        }

        return ticketRepository.save(existingTicket);
    }

}
