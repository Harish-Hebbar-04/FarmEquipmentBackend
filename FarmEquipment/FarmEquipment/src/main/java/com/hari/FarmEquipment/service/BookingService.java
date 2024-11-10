// src/main/java/com/hari/FarmEquipment/service/BookingService.java

package com.hari.FarmEquipment.service;

import com.hari.FarmEquipment.model.Booking;
import com.hari.FarmEquipment.model.Equipment;
import com.hari.FarmEquipment.model.User;
import com.hari.FarmEquipment.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsForFarmer(User farmer) {
        return bookingRepository.findByFarmer(farmer);
    }
    public List<Booking> getBookingsForUser(String username) {
        return bookingRepository.findByFarmerUsername(username);
    }
}
