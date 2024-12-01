package com.example.project.api.dto;

import java.util.List;

public record ClassDto(String className, String lector, String room, List<String> members) {
}
