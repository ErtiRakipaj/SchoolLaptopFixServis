package com.task.SchoolLaptopFixServis.repositories;

import com.task.SchoolLaptopFixServis.models.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaptopRepository extends JpaRepository<Laptop,Long> {
    Optional<Laptop> findLaptopById(Long id);
    Optional<Laptop> findLaptopByOwner(String owner);
}
