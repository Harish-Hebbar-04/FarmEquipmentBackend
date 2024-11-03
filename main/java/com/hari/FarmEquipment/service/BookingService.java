package com.hari.FarmEquipment.service;

import com.hari.FarmEquipment.model.Booking;
import com.hari.FarmEquipment.model.BookingStatus;
import com.hari.FarmEquipment.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Create a new booking (farmer creates it)
    public Booking createBooking(Booking booking) {
        booking.setStatus(BookingStatus.PENDING); // Default status
        return bookingRepository.save(booking);
    }

    // Get all bookings for a specific farmer
    public List<Booking> getBookingsForFarmer(Long farmerId) {
        return bookingRepository.findByFarmerId(farmerId);
    }

    // Get all bookings for a supplier's equipment
    public List<Booking> getBookingsForSupplier(String ownerDetails) {
        return bookingRepository.findByEquipmentOwnerDetails(ownerDetails);
    }

    // Update the status of the booking (suppliers update)
    public Optional<Booking> updateBookingStatus(Long bookingId, BookingStatus status) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            booking.get().setStatus(status);
            bookingRepository.save(booking.get());
        }
        return booking;
    }
}
