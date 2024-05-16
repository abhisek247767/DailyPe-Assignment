package com.assignment.dailype.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.dailype.Model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
}