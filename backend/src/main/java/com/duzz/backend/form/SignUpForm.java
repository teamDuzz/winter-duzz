package com.duzz.backend.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpForm {
    @Schema(description = "학번", example = "202102710")
    private String id;
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Schema(description = "비밀번호", example = "1234")
    private String password;
    @Schema(description = "멘토인지 여부", example = "false")
    private Boolean isMentor;
}
