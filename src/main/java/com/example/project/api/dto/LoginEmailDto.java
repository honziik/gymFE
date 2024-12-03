package com.example.project.api.dto;

import lombok.Builder;

@Builder
public record LoginEmailDto(String login, String email, int balance) {}