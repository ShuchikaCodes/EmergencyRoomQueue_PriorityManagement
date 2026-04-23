package com.pesu.er.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Min(0)
    @Max(120)
    private int age;

    @NotBlank
    @Column(length = 500)
    private String symptoms;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ArrivalMode arrivalMode;

    private int severityLevel;

    private int priorityScore;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    private String assignedWard;

    private String treatedByDoctor;

    @Column(length = 500)
    private String latestNote;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void updateStatus(PatientStatus status) {
        this.status = status;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = PatientStatus.ARRIVED;
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public ArrivalMode getArrivalMode() {
        return arrivalMode;
    }

    public void setArrivalMode(ArrivalMode arrivalMode) {
        this.arrivalMode = arrivalMode;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(int severityLevel) {
        this.severityLevel = severityLevel;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public String getAssignedWard() {
        return assignedWard;
    }

    public void setAssignedWard(String assignedWard) {
        this.assignedWard = assignedWard;
    }

    public String getTreatedByDoctor() {
        return treatedByDoctor;
    }

    public void setTreatedByDoctor(String treatedByDoctor) {
        this.treatedByDoctor = treatedByDoctor;
    }

    public String getLatestNote() {
        return latestNote;
    }

    public void setLatestNote(String latestNote) {
        this.latestNote = latestNote;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
