package com.pesu.er.pattern.strategy;

import com.pesu.er.model.ArrivalMode;
import com.pesu.er.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class DefaultPriorityStrategy implements PriorityStrategy {

    @Override
    public int calculatePriority(Patient patient, int severityLevel) {
        int baseScore = severityLevel * 10;
        int elderlyBonus = patient.getAge() >= 60 ? 5 : 0;
        int ambulanceBonus = patient.getArrivalMode() == ArrivalMode.AMBULANCE ? 5 : 0;
        return baseScore + elderlyBonus + ambulanceBonus;
    }
}
