package com.task.SchoolLaptopFixServis.requests;

import com.task.SchoolLaptopFixServis.enums.Status;
import lombok.Data;

import java.util.List;

@Data
public class UpdateTicketRequest {
    private String description;
    private List<String> laptopParts;
    private Status ticketStatus;

}
