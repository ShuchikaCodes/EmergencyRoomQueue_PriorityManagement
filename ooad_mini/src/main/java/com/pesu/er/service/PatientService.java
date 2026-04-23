package com.pesu.er.service;

import com.pesu.er.dto.PatientRegistrationRequest;
import com.pesu.er.model.Patient;
import com.pesu.er.repository.PatientRepository;
import com.pesu.er.pattern.factory.PatientFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientFactory patientFactory;

    public PatientService(PatientRepository patientRepository, PatientFactory patientFactory) {
        this.patientRepository = patientRepository;
        this.patientFactory = patientFactory;
    }

    public Patient registerPatient(PatientRegistrationRequest request) {
        Patient patient = patientFactory.createPatient(request);
        return patientRepository.save(patient);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAllByOrderByCreatedAtDesc();
    }

    public Patient findPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + id));
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
}
