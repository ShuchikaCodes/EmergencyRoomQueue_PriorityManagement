package com.pesu.er.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PriorityOverrideRequest {

    @Min(1)
    @Max(15)
    private int newPriorityScore;

    private String note;

    public int getNewPriorityScore() {
        return newPriorityScore;
    }

    public void setNewPriorityScore(int newPriorityScore) {
        this.newPriorityScore = newPriorityScore;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
