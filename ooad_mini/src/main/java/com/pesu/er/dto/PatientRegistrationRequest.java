package com.pesu.er.dto;

import com.pesu.er.model.ArrivalMode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PatientRegistrationRequest {

    @NotBlank
    private String name;

    @Min(0)
    @Max(120)
    private int age;

    @NotBlank
    private String symptoms;

    @NotNull
    private ArrivalMode arrivalMode;

    private String assignedWard;

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

    public String getAssignedWard() {
        return assignedWard;
    }

    public void setAssignedWard(String assignedWard) {
        this.assignedWard = assignedWard;
    }
}
