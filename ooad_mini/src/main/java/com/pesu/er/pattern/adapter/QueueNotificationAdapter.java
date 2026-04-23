package com.pesu.er.pattern.adapter;

import com.pesu.er.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class QueueNotificationAdapter implements CaretakerMessageAdapter {

    @Override
    public String buildMessage(String eventType, Patient patient, String note) {
        return "Caretaker update [" + eventType + "]: Patient " + patient.getName()
                + " is now " + patient.getStatus()
                + ". Note: " + note;
    }
}
