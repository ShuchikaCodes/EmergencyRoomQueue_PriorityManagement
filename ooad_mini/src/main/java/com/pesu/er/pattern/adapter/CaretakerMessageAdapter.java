package com.pesu.er.pattern.adapter;

import com.pesu.er.model.Patient;

public interface CaretakerMessageAdapter {

    String buildMessage(String eventType, Patient patient, String note);
}
