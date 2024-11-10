//package com.hari.FarmEquipment.controller;
//
//import com.hari.FarmEquipment.model.Equipment;
//import com.hari.FarmEquipment.service.EquipmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//
//
//@RestController
//@RequestMapping("/api/equipment")
//@CrossOrigin(origins = "http://localhost:5173")
//public class EquipmentController {
//    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);
//
//    @Autowired
//    private EquipmentService equipmentService;
//
//    @PostMapping("/add")
//    public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment equipment) {
//        try {
//            Equipment savedEquipment = equipmentService.addEquipment(equipment);
//            return new ResponseEntity<>(savedEquipment, HttpStatus.CREATED);
//        } catch (Exception e) {
//            logger.error("Error adding equipment: {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @GetMapping("/available")
//    public ResponseEntity<List<Equipment>> getAvailableEquipment() {
//        List<Equipment> availableEquipment = equipmentService.getAvailableEquipment();
//        return new ResponseEntity<>(availableEquipment, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteEquipment(@PathVariable Long id) {
//        try {
//            boolean isDeleted = equipmentService.deleteEquipment(id);
//            if (isDeleted) {
//                return new ResponseEntity<>("Equipment deleted successfully", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Equipment not found", HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @GetMapping("/search")
//    public ResponseEntity<List<Equipment>> searchEquipment(
//            @RequestParam(required = false) String keyword,
//            @RequestParam(required = false) String location,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) Double minPrice,
//            @RequestParam(required = false) Double maxPrice,
//            @RequestParam(required = false) String availableDate) {
//
//        List<Equipment> filteredEquipment = equipmentService.searchEquipment(
//                keyword, location, type, minPrice, maxPrice, availableDate);
//
//        return ResponseEntity.ok(filteredEquipment);
//    }
//
//}
// src/main/java/com/hari/FarmEquipment/controller/EquipmentController.java

package com.hari.FarmEquipment.controller;

import com.hari.FarmEquipment.model.Booking;
import com.hari.FarmEquipment.model.BookingStatus;
import com.hari.FarmEquipment.model.Equipment;
import com.hari.FarmEquipment.model.User;
import com.hari.FarmEquipment.repo.BookingRepository;
import com.hari.FarmEquipment.repo.EquipmentRepository;
import com.hari.FarmEquipment.repo.UserRepository;
import com.hari.FarmEquipment.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@CrossOrigin(origins = "http://localhost:5173")
public class EquipmentController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;
        @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepository.findByRentalStatus("available" );
    }

    @PostMapping("/add")
    public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment equipment) {
        Equipment addedEquipment = equipmentService.addEquipment(equipment);
        return new ResponseEntity<>(addedEquipment, HttpStatus.CREATED);
    }

    @PostMapping("/book")
    public Booking bookEquipment(@RequestParam Long equipmentId, @RequestParam String username) {
        User farmer = userRepository.findByUsername(username);
        Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow(() -> new RuntimeException("Equipment not found"));

        Booking booking = new Booking();
        booking.setFarmer(farmer);
        booking.setEquipment(equipment);
        booking.setBookingStart(LocalDate.now());
        booking.setBookingEnd(LocalDate.now().plusDays(7)); // example duration
        booking.setStatus(BookingStatus.PENDING);

        equipment.setRentalStatus("rented");
        equipmentRepository.save(equipment);

        return bookingRepository.save(booking);
    }
}
