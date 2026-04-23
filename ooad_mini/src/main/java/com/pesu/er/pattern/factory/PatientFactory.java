package com.pesu.er.pattern.factory;

import com.pesu.er.dto.PatientRegistrationRequest;
import com.pesu.er.model.Patient;
import com.pesu.er.model.PatientStatus;
import org.springframework.stereotype.Component;

@Component
public class PatientFactory {

    public Patient createPatient(PatientRegistrationRequest request) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setSymptoms(request.getSymptoms());
        patient.setArrivalMode(request.getArrivalMode());
        patient.setAssignedWard(request.getAssignedWard());
        patient.setPriorityScore(0);
        patient.setSeverityLevel(0);
        patient.setLatestNote("Patient registered");
        patient.setStatus(PatientStatus.REGISTERED);
        return patient;
    }
}
