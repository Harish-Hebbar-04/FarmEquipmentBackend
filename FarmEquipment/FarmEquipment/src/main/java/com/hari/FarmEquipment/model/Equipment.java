// src/main/java/com/hari/FarmEquipment/model/Equipment.java

package com.hari.FarmEquipment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate dateOfAvailability;
    private String equipmentType;
    private String condition;
    private String location;
    private int rentalDuration;
    private String ownerDetails;
    private String rentalStatus; // e.g., available, rented, maintenance
    private String imageUrl;
    private BigDecimal depositAmount;
}
