package com.duzz.backend.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityProvider {
    @Value("${security.admin}")
    private String adminId;
    @Value("${security.check-admin}")
    private boolean checkAdmin;

    public String getCurrentUserId() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null || authentication.getName().equals("anonymousUser")) {
            throw new SecurityException("이 작업을 수행하려면 로그인이 필요합니다.");
        }

        return authentication.getName();
    }

    public void checkAdmin() {
        if (checkAdmin && !getCurrentUserId().equals(adminId)) {
            throw new SecurityException("관리자만 접근할 수 있습니다.");
        }
    }
}
