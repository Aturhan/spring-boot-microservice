package com.abdullahturhan.model;

public enum TaxiStatus {
    FULL("full"),
    EMPTY("empty"),
    ;

    private final String value;

    TaxiStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
