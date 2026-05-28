package com.example.minibank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtAuthResponse {
    private final String accessToken;
    private final String tokenType = "Bearer";
}
