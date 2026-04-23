package com.pesu.er.pattern.strategy;

import com.pesu.er.model.Patient;

public interface PriorityStrategy {

    int calculatePriority(Patient patient, int severityLevel);
}
