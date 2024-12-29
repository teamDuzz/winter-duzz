package com.duzz.backend.controller;

import com.duzz.backend.form.JwtTokenDto;
import com.duzz.backend.form.SignInForm;
import com.duzz.backend.form.SignUpForm;
import com.duzz.backend.service.MemberService;
import com.duzz.backend.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Tag(name = "Member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("아아 마이크 테스트");
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입을 시도합니다.")
    public ResponseEntity<?> signUp(@RequestBody SignUpForm form) {
        try {
            memberService.signUp(form);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    @Operation(summary = "로그인을 시도합니다.")
    public ResponseEntity<?> signIn(@RequestBody SignInForm form) {
        try {
            String token = memberService.signIn(form);
            JwtTokenDto jwt = JwtTokenDto.builder()
                    .grantType("Bearer")
                    .accessToken(token)
                    .build();
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            JwtTokenDto empty = JwtTokenDto.builder()
                    .grantType("Bearer")
                    .accessToken("")
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(empty);
        }
    }

    @GetMapping("/me")
    @Operation(summary = "전송된 JWT 토큰을 기반으로 내 정보를 반환합니다. Swagger에서는 불가능하고 Postman 등을 사용해야 함.")
    public ResponseEntity<?> getMyInfo() {
        try {
            return ResponseEntity.ok(SecurityUtil.getCurrentUserId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
