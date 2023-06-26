package com.task.SchoolLaptopFixServis.repositories;

import com.task.SchoolLaptopFixServis.models.LaptopPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LaptopPartRepository extends JpaRepository<LaptopPart,Long> {

    Optional<LaptopPart> findLaptopPartById(Long id);
    Optional<LaptopPart> findLaptopPartByNameIgnoreCase(String name);

    List<LaptopPart> findAllByNameIgnoreCaseIn(List<String> name);
    void deleteLaptopPartById(Long id);
}
