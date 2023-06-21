package com.task.SchoolLaptopFixServis.enums;

public enum Status {
    OPEN ("OPEN"),
    CLOSED("CLOSED");
    private final String status;

    Status(String status) {
        this.status = status;
    }
}
