package com.task.SchoolLaptopFixServis.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateLaptopPartRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
}
