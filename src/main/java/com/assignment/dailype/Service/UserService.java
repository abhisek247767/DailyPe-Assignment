package com.assignment.dailype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.assignment.dailype.Model.User;

public interface UserService {
    String createUser(User user);

	List<User> getAllUsers();

	Optional<User> getUserById(UUID userId);

	Optional<User> getUserByMobileNumber(String mobNum);

	List<User> getUsersByManagerId(UUID managerId);
}

