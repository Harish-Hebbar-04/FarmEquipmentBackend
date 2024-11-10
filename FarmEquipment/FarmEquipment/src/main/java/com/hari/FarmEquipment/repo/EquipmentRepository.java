package com.hari.FarmEquipment.repo;

import com.hari.FarmEquipment.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByDateOfAvailabilityAfter(LocalDate date);

    List<Equipment> findByRentalStatus(String available);
}
