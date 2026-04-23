package com.pesu.er.repository;

import com.pesu.er.model.Patient;
import com.pesu.er.model.PatientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByStatusInOrderByPriorityScoreDescCreatedAtAsc(Collection<PatientStatus> statuses);

    List<Patient> findAllByOrderByCreatedAtDesc();

    Optional<Patient> findFirstByStatusInOrderByPriorityScoreDescCreatedAtAsc(Collection<PatientStatus> statuses);
}
