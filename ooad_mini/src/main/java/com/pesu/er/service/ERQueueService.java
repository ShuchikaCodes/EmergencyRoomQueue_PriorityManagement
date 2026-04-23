package com.pesu.er.service;

import com.pesu.er.dto.PriorityOverrideRequest;
import com.pesu.er.dto.TreatmentRequest;
import com.pesu.er.dto.TriageRequest;
import com.pesu.er.model.Patient;
import com.pesu.er.model.PatientStatus;
import com.pesu.er.pattern.observer.PatientEventPublisher;
import com.pesu.er.pattern.strategy.PriorityStrategy;
import com.pesu.er.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ERQueueService {

    private static final Set<PatientStatus> ACTIVE_QUEUE_STATUSES = Set.of(
            PatientStatus.PRIORITY_ASSIGNED,
            PatientStatus.QUEUED,
            PatientStatus.PRIORITY_OVERRIDDEN
    );

    private final PatientRepository patientRepository;
    private final PriorityStrategy priorityStrategy;
    private final PatientEventPublisher patientEventPublisher;

    public ERQueueService(PatientRepository patientRepository,
                          PriorityStrategy priorityStrategy,
                          PatientEventPublisher patientEventPublisher) {
        this.patientRepository = patientRepository;
        this.priorityStrategy = priorityStrategy;
        this.patientEventPublisher = patientEventPublisher;
    }

    @Transactional
    public Patient assessAndQueue(Long patientId, TriageRequest request) {
        Patient patient = getPatient(patientId);
        patient.setStatus(PatientStatus.UNDER_ASSESSMENT);
        patient.setSeverityLevel(request.getSeverityLevel());
        patient.setLatestNote(request.getAssessmentNote());

        int priority = priorityStrategy.calculatePriority(patient, request.getSeverityLevel());
        patient.setPriorityScore(priority);
        patient.setStatus(PatientStatus.QUEUED);

        Patient savedPatient = patientRepository.save(patient);
        patientEventPublisher.publish("PRIORITY_ASSIGNED", savedPatient, "Priority set to " + priority);
        return savedPatient;
    }

    public List<Patient> getQueue() {
        return patientRepository.findByStatusInOrderByPriorityScoreDescCreatedAtAsc(ACTIVE_QUEUE_STATUSES);
    }

    public Patient getNextPatient() {
        return patientRepository.findFirstByStatusInOrderByPriorityScoreDescCreatedAtAsc(ACTIVE_QUEUE_STATUSES)
                .orElse(null);
    }

    @Transactional
    public Patient overridePriority(Long patientId, PriorityOverrideRequest request) {
        Patient patient = getPatient(patientId);
        patient.setPriorityScore(request.getNewPriorityScore());
        patient.setLatestNote(request.getNote());
        patient.setStatus(PatientStatus.PRIORITY_OVERRIDDEN);
        Patient savedPatient = patientRepository.save(patient);
        patientEventPublisher.publish("PRIORITY_OVERRIDDEN", savedPatient, request.getNote());
        return savedPatient;
    }

    @Transactional
    public Patient startTreatment(Long patientId, TreatmentRequest request) {
        Patient patient = getPatient(patientId);
        patient.setStatus(PatientStatus.UNDER_TREATMENT);
        patient.setTreatedByDoctor(request.getDoctorName());
        patient.setLatestNote(request.getTreatmentNote());
        Patient savedPatient = patientRepository.save(patient);
        patientEventPublisher.publish("UNDER_TREATMENT", savedPatient, request.getTreatmentNote());
        return savedPatient;
    }

    @Transactional
    public Patient dischargePatient(Long patientId, String dischargeNote) {
        Patient patient = getPatient(patientId);
        patient.setStatus(PatientStatus.DISCHARGED);
        patient.setLatestNote(dischargeNote);
        Patient savedPatient = patientRepository.save(patient);
        patientEventPublisher.publish("DISCHARGED", savedPatient, dischargeNote);
        return savedPatient;
    }

    private Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + id));
    }
}
