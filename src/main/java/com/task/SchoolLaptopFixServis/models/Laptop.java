package com.task.SchoolLaptopFixServis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "laptops")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String owner;

    @OneToOne(mappedBy = "laptop")
    private transient Ticket ticket;
}
