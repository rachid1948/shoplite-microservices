package com.shoplite.order_service.dto.external;

public record CustomerSnapshotDto(
        Long id,
        String firstName,
        String lastName,
        String email
) {
    public String fullName() {
        String fn = firstName == null ? "" : firstName;
        String ln = lastName == null ? "" : lastName;
        return (fn + " " + ln).trim();
    }
}