package com.pesu.er.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class TriageRequest {

    @Min(1)
    @Max(10)
    private int severityLevel;

    private String assessmentNote;

    public int getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(int severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getAssessmentNote() {
        return assessmentNote;
    }

    public void setAssessmentNote(String assessmentNote) {
        this.assessmentNote = assessmentNote;
    }
}
