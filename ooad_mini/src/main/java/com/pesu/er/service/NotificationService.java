package com.pesu.er.service;

import com.pesu.er.model.NotificationLog;
import com.pesu.er.model.Patient;
import com.pesu.er.pattern.adapter.CaretakerMessageAdapter;
import com.pesu.er.pattern.observer.PatientEventObserver;
import com.pesu.er.repository.NotificationLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService implements PatientEventObserver {

    private final NotificationLogRepository notificationLogRepository;
    private final CaretakerMessageAdapter caretakerMessageAdapter;

    public NotificationService(NotificationLogRepository notificationLogRepository,
                               CaretakerMessageAdapter caretakerMessageAdapter) {
        this.notificationLogRepository = notificationLogRepository;
        this.caretakerMessageAdapter = caretakerMessageAdapter;
    }

    @Override
    public void onPatientEvent(String eventType, Patient patient, String note) {
        String message = caretakerMessageAdapter.buildMessage(eventType, patient, note);
        notificationLogRepository.save(new NotificationLog("CARETAKER_DASHBOARD", message, LocalDateTime.now()));
    }

    public List<NotificationLog> recentNotifications() {
        return notificationLogRepository.findTop10ByOrderByCreatedAtDesc();
    }
}
