package com.pesu.er.pattern.observer;

import com.pesu.er.model.Patient;

public interface PatientEventObserver {

    void onPatientEvent(String eventType, Patient patient, String note);
}
