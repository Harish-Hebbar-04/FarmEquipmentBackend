package com.hari.FarmEquipment.repo;

import com.hari.FarmEquipment.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByFarmerId(Long farmerId);
    List<Booking> findByEquipmentOwnerDetails(String ownerDetails);
}
