package com.example.project.api.dto;

public class EntryDtoFactory {

    private EntryDtoFactory() {
    }

    public static EntryDto createWithTimes(String entryTime, String leaveTime) {
        return new EntryDto(entryTime, leaveTime);
    }

    public static EntryDto createWithEntryTimeOnly(String entryTime) {
        return new EntryDto(entryTime, null);
    }

    public static EntryDto createWithDefaultTimes() {
        return new EntryDto("00:00", "00:00");
    }
}
