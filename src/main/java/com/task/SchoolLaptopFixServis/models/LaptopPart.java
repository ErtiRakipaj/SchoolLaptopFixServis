package com.task.SchoolLaptopFixServis.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "laptopParts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LaptopPart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    @ManyToMany(mappedBy = "laptopParts", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ticket> tickets;
}
