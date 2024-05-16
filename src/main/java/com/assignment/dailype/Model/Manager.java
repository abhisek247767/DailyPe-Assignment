package com.assignment.dailype.Model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Manager")
public class Manager {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private UUID managerId;
    private String fullName;

    // Constructors
    public Manager() {
        // Default constructor
    }

    // Getters and setters
    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
