package com.hari.FarmEquipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("http://localhost:5173")
public class FarmEquipmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(FarmEquipmentApplication.class, args);
	}
}
