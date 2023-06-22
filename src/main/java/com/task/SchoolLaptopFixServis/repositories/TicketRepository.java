package com.task.SchoolLaptopFixServis.repositories;

import com.task.SchoolLaptopFixServis.enums.Status;
import com.task.SchoolLaptopFixServis.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Optional<Ticket> findTicketByLaptop_Id(Long laptopId);
    List<Ticket> findTicketsByLaptop_Owner(String owner);
    Optional<Ticket> findTicketById(Long id);

    Optional<Ticket>findTicketByLaptopOwnerAndTicketStatus(String laptopOwner, Status status);
}
