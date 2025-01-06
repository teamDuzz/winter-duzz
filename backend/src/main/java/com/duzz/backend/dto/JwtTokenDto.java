package com.duzz.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtTokenDto {
    private String grantType;
    private String accessToken;
}
