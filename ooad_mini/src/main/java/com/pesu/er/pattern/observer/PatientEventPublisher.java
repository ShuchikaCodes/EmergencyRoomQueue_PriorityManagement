package com.pesu.er.pattern.observer;

import com.pesu.er.model.Patient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientEventPublisher {

    private final List<PatientEventObserver> observers;

    public PatientEventPublisher(List<PatientEventObserver> observers) {
        this.observers = observers;
    }

    public void publish(String eventType, Patient patient, String note) {
        for (PatientEventObserver observer : observers) {
            observer.onPatientEvent(eventType, patient, note);
        }
    }
}
