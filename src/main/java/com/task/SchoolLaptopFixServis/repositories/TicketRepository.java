package com.task.SchoolLaptopFixServis.repositories;

import com.task.SchoolLaptopFixServis.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
