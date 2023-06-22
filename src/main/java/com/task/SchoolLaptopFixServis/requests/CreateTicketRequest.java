package com.task.SchoolLaptopFixServis.requests;

import com.task.SchoolLaptopFixServis.enums.Status;
import lombok.Data;

import java.util.List;

@Data
public class CreateTicketRequest {

    private Long laptopId;
    private List<String> partNames;
    private String description;

}
