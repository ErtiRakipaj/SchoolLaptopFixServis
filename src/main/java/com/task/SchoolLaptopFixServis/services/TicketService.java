package com.task.SchoolLaptopFixServis.services;

import com.task.SchoolLaptopFixServis.enums.Status;
import com.task.SchoolLaptopFixServis.models.Laptop;
import com.task.SchoolLaptopFixServis.models.LaptopPart;
import com.task.SchoolLaptopFixServis.models.Ticket;
import com.task.SchoolLaptopFixServis.repositories.LaptopPartRepository;
import com.task.SchoolLaptopFixServis.repositories.LaptopRepository;
import com.task.SchoolLaptopFixServis.repositories.TicketRepository;
import com.task.SchoolLaptopFixServis.requests.CreateTicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Ticket createTicket(CreateTicketRequest ticketRequest){
        Laptop laptop = laptopRepository.findById(ticketRequest.getLaptopId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid laptop ID"));
        List<LaptopPart> laptopParts = partRepository.findAllByNameIgnoreCaseIn(ticketRequest.getPartNames());

        Ticket ticket = Ticket.builder()
                .laptop(laptop)
                //.laptopParts(laptopParts)
                .description(ticketRequest.getDescription())
                .ticketStatus(Status.OPEN)
                .build();
        for (LaptopPart part: laptopParts){
            part.setTicket(ticket);
        }
            ticket.setLaptopParts(laptopParts);


        return ticketRepository.save(ticket);

    }


//    private Laptop getLaptopFromRequestId(Long id) {
//        return laptopRepository.findLaptopById(id).orElseThrow(
//                ()-> new RuntimeException("Laptop not found")
//        );
//    }

//    private List<LaptopPart> getLaptopPartsFromRequestedPartNames(List<String> parts){
//        List<LaptopPart> neededParts = new ArrayList<>();
//        for (String part: parts){
//            LaptopPart foundPart = partRepository.findLaptopPartByNameIgnoreCase(part).orElseThrow(
//                    ()-> new RuntimeException("Part "+part+" not found")
//            );
//           neededParts.add(foundPart);
//        }
//        return neededParts;
//        }



}
