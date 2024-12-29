package com.duzz.backend.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpForm {
    private String id;
    private String name;
    private String password;
}
