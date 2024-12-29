package com.duzz.backend.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtTokenDto {
    private String grantType;
    private String accessToken;
}
