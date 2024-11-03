package com.hari.FarmEquipment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer; // Assuming you have a User entity representing farmers and suppliers

    private LocalDate bookingStart;
    private LocalDate bookingEnd;

    @Enumerated(EnumType.STRING)
    public BookingStatus status; // PENDING, CONFIRMED, CANCELLED, COMPLETED
}

