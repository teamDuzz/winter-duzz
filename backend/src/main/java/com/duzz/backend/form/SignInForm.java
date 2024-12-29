package com.duzz.backend.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInForm {
    private String id;
    private String password;
}
