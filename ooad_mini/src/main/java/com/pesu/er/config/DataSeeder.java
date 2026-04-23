package com.pesu.er.config;

import com.pesu.er.dto.PatientRegistrationRequest;
import com.pesu.er.dto.TriageRequest;
import com.pesu.er.model.ArrivalMode;
import com.pesu.er.repository.PatientRepository;
import com.pesu.er.service.ERQueueService;
import com.pesu.er.service.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDemoData(PatientRepository patientRepository,
                                   PatientService patientService,
                                   ERQueueService queueService) {
        return args -> {
            if (patientRepository.count() > 0) {
                return;
            }

            PatientRegistrationRequest first = new PatientRegistrationRequest();
            first.setName("Rahul Sharma");
            first.setAge(68);
            first.setSymptoms("Chest pain and breathing difficulty");
            first.setArrivalMode(ArrivalMode.AMBULANCE);
            first.setAssignedWard("Ward A");
            Long firstId = patientService.registerPatient(first).getId();

            TriageRequest firstTriage = new TriageRequest();
            firstTriage.setSeverityLevel(9);
            firstTriage.setAssessmentNote("Immediate cardiac attention required");
            queueService.assessAndQueue(firstId, firstTriage);

            PatientRegistrationRequest second = new PatientRegistrationRequest();
            second.setName("Ananya Rao");
            second.setAge(24);
            second.setSymptoms("High fever and dehydration");
            second.setArrivalMode(ArrivalMode.WALK_IN);
            second.setAssignedWard("Ward B");
            Long secondId = patientService.registerPatient(second).getId();

            TriageRequest secondTriage = new TriageRequest();
            secondTriage.setSeverityLevel(6);
            secondTriage.setAssessmentNote("Needs monitoring and fluids");
            queueService.assessAndQueue(secondId, secondTriage);
        };
    }
}
