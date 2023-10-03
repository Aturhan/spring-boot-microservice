package com.abdullahturhan.model;

public enum TravelHasFinished {
    FINISHED("Finished"),
    NOT_FINISHED("Not Finished");

    private final String value;

    TravelHasFinished(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
