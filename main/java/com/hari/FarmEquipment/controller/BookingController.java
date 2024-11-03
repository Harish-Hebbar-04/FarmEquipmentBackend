package com.hari.FarmEquipment.controller;

import com.hari.FarmEquipment.model.Booking;
import com.hari.FarmEquipment.model.BookingStatus;
import com.hari.FarmEquipment.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create a booking (farmers)
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(createdBooking);
    }

    // View bookings for farmers
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Booking>> getBookingsForFarmer(@PathVariable Long farmerId) {
        List<Booking> bookings = bookingService.getBookingsForFarmer(farmerId);
        return ResponseEntity.ok(bookings);
    }

    // View bookings for suppliers (supplier checks their equipment bookings)
    @GetMapping("/supplier/{ownerDetails}")
    public ResponseEntity<List<Booking>> getBookingsForSupplier(@PathVariable String ownerDetails) {
        List<Booking> bookings = bookingService.getBookingsForSupplier(ownerDetails);
        return ResponseEntity.ok(bookings);
    }

    // Update booking status (suppliers)
    @PatchMapping("/{id}")
    public ResponseEntity<Booking> updateBookingStatus(@PathVariable Long id, @RequestParam BookingStatus status) {
        Optional<Booking> updatedBooking = bookingService.updateBookingStatus(id, status);
        return updatedBooking.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
