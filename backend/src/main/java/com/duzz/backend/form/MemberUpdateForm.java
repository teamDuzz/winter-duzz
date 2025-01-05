package com.duzz.backend.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberUpdateForm {
    private String name;
    private String password;
    private String phone;
    private String email;
    private String major;
    private Boolean isMentor;
}
