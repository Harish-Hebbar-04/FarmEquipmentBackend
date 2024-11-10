// src/main/java/com/hari/FarmEquipment/controller/BookingController.java

package com.hari.FarmEquipment.controller;

import com.hari.FarmEquipment.model.Booking;
import com.hari.FarmEquipment.model.BookingStatus;
import com.hari.FarmEquipment.model.Equipment;
import com.hari.FarmEquipment.model.User;
import com.hari.FarmEquipment.service.BookingService;
import com.hari.FarmEquipment.service.EquipmentService;
import com.hari.FarmEquipment.service.JwtService;
import com.hari.FarmEquipment.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(
            @RequestHeader("Authorization") String token,
            @RequestParam Long equipmentId,
            @RequestBody Booking bookingDetails) {

        // Extract the username from JWT token
        String username = jwtService.extractUserName(token.replace("Bearer ", ""));

        // Find the farmer by username
        Optional<User> farmerOptional = userService.findByUsername(username);
        if (farmerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Farmer not found.");
        }
        User farmer = farmerOptional.get();

        // Find the equipment by ID
        Optional<Equipment> equipmentOptional = equipmentService.findById(equipmentId);
        if (equipmentOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Equipment not found.");
        }
        Equipment equipment = equipmentOptional.get();

        // Set booking details
        Booking booking = new Booking();
        booking.setFarmer(farmer);
        booking.setEquipment(equipment);
        booking.setBookingStart(bookingDetails.getBookingStart());
        booking.setBookingEnd(bookingDetails.getBookingEnd());
        booking.setStatus(BookingStatus.CONFIRMED);

        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(createdBooking);
    }
    // src/main/java/com/hari/FarmEquipment/controller/BookingController.java



        @GetMapping("/my-bookings")
        public ResponseEntity<List<Booking>> getMyBookings(@RequestHeader("Authorization") String token) {
            String username = jwtService.extractUserName(token.substring(7)); // Extract username from JWT token
            List<Booking> bookings = bookingService.getBookingsForUser(username);
            return ResponseEntity.ok(bookings);
        }


}
