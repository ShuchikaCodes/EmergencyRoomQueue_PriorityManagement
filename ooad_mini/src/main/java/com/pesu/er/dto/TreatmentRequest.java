package com.pesu.er.dto;

import jakarta.validation.constraints.NotBlank;

public class TreatmentRequest {

    @NotBlank
    private String doctorName;

    @NotBlank
    private String treatmentNote;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTreatmentNote() {
        return treatmentNote;
    }

    public void setTreatmentNote(String treatmentNote) {
        this.treatmentNote = treatmentNote;
    }
}
