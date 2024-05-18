package com.assignment.dailype.Service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.dailype.Model.User;
import com.assignment.dailype.Repository.ManagerRepository;
import com.assignment.dailype.Repository.UserRepository;
import com.assignment.dailype.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public String createUser(User user) {
        // Perform validations
        if (user.getFullName() == null || user.getFullName().isEmpty()) {
            return "Full name must not be empty";
        }
        if (!isValidMobileNumber(user.getMobNum())) {
            return "Invalid mobile number";
        }
        if (!isValidPAN(user.getPanNum())) {
            return "Invalid PAN number";
        }
        if (!isValidManagerId(user.getManagerId())) {
            return "Invalid manager ID";
        }

        // Set default values
        user.setUserId(UUID.randomUUID());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(null);
        user.setActive(true);

        // Save user to the database
        userRepository.save(user);

        return "User created successfully";
    }

    private boolean isValidMobileNumber(String mobileNumber) {
        // Implement mobile number validation logic
        // For example, check if it's a valid 10-digit number
        return mobileNumber != null && mobileNumber.matches("\\d{10}");
    }

    private boolean isValidPAN(String panNumber) {
        // Implement PAN number validation logic
        // For example, check if it's in the correct format (e.g., AABCP1234C)
        return panNumber != null && panNumber.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}");
    }

    private boolean isValidManagerId(UUID managerId) {
        // Check if the manager ID exists in the manager table
        return managerId != null;
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByMobileNumber(String mobNum) {
        return userRepository.findByMobNum(mobNum);
    }

    @Override
    public List<User> getUsersByManagerId(UUID managerId) {
        return userRepository.findByManagerId(managerId);
    }
    
    @Override
    public String deleteUserById(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return "User deleted successfully";
        }
        return "User ID not found";
    }

    @Override
    public String deleteUserByMobileNumber(String mobNum) {
        Optional<User> user = userRepository.findByMobNum(mobNum);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "User deleted successfully";
        }
        return "Mobile number not found";
    }
}
