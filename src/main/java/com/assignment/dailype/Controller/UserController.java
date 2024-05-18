package com.assignment.dailype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.dailype.Model.User;
import com.assignment.dailype.Requests.DeleteUserRequest;
import com.assignment.dailype.Requests.UpdateUserRequest;
import com.assignment.dailype.Service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String message = userService.createUser(user);
        return ResponseEntity.ok(message);
    }
    
    @PostMapping("/get_users")
    public ResponseEntity<?> getUsers(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) String mobNum,
            @RequestParam(required = false) UUID managerId) {

        if (userId != null) {
            Optional<User> user = userService.getUserById(userId);
            return ResponseEntity.ok().body(user.isPresent() ? List.of(user.get()) : List.of());
        }

        if (mobNum != null) {
            Optional<User> user = userService.getUserByMobileNumber(mobNum);
            return ResponseEntity.ok().body(user.isPresent() ? List.of(user.get()) : List.of());
        }

        if (managerId != null) {
            List<User> users = userService.getUsersByManagerId(managerId);
            return ResponseEntity.ok().body(users);
        }

        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
    
    
    
    @PostMapping("/delete_user")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserRequest deleteUserRequest) {
        String result;
        if (deleteUserRequest.getUserId() != null) {
            UUID userId = UUID.fromString(deleteUserRequest.getUserId());
            result = userService.deleteUserById(userId);
        } else if (deleteUserRequest.getMobNum() != null) {
            result = userService.deleteUserByMobileNumber(deleteUserRequest.getMobNum());
        } else {
            return ResponseEntity.badRequest().body("Either user_id or mob_num must be provided");
        }
        if (result.equals("User deleted successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    
    
    @PostMapping("/update_user")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody Map<String, Object> request) {
        List<String> userIds = (List<String>) request.get("user_ids");
        Map<String, Object> updateData = (Map<String, Object>) request.get("update_data");

        String result = userService.updateUsers(userIds, updateData);

        return ResponseEntity.ok(Collections.singletonMap("message", result));
    }
}