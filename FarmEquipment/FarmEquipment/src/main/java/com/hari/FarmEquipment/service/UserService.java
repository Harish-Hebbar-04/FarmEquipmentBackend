package com.hari.FarmEquipment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hari.FarmEquipment.repo.UserRepository;
import com.hari.FarmEquipment.model.User;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return repo.save(user) ;

    }
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(repo.findByUsername(username));
    }
}