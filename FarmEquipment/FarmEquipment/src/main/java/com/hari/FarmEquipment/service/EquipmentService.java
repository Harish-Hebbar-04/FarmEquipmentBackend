package com.hari.FarmEquipment.service;

import com.hari.FarmEquipment.model.Equipment;
import com.hari.FarmEquipment.repo.EquipmentRepository;
import com.hari.FarmEquipment.repo.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAvailableEquipment() {
        return equipmentRepository.findByDateOfAvailabilityAfter(LocalDate.now());
    }
    public void removeEquipment(Long id){
        equipmentRepository.deleteById(id);
    }

    public boolean deleteEquipment(Long id) {
        if (equipmentRepository.existsById(id)) {
            equipmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Equipment> searchEquipment(String keyword, String location, String type, Double minPrice, Double maxPrice, String availableDate) {
        List<Equipment> allEquipment = equipmentRepository.findAll();

        return allEquipment.stream()
                .filter(equipment -> (keyword == null || equipment.getName().toLowerCase().contains(keyword.toLowerCase())))
                .filter(equipment -> (location == null || equipment.getLocation().equalsIgnoreCase(location)))
//                .filter(equipment -> (type == null || equipment.getType().equalsIgnoreCase(type)))
//                .filter(equipment -> (minPrice == null || equipment.getPrice() >= minPrice))
//                .filter(equipment -> (maxPrice == null || equipment.getPrice() <= maxPrice))
//                .filter(equipment -> (availableDate == null || equipment.getAvailableDate().equals(availableDate)))
                .collect(Collectors.toList());
    }
    public Optional<Equipment> findById(Long id) {
        return equipmentRepository.findById(id);
    }
}
