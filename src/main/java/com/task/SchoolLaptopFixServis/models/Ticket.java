package com.task.SchoolLaptopFixServis.models;

import com.task.SchoolLaptopFixServis.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<LaptopPart> laptopParts;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status ticketStatus;


}
