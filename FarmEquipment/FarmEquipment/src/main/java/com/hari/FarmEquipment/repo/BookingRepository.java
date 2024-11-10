// src/main/java/com/hari/FarmEquipment/repository/BookingRepository.java

package com.hari.FarmEquipment.repo;

import com.hari.FarmEquipment.model.Booking;
import com.hari.FarmEquipment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByFarmer(User farmer);
    List<Booking> findByFarmerUsername(String username);
}
