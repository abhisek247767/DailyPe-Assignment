package com.assignment.dailype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.dailype.Model.Manager;

import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {

}
