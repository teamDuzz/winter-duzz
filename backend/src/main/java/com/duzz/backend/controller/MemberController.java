package com.duzz.backend.controller;

import com.duzz.backend.form.JwtTokenDto;
import com.duzz.backend.form.MemberUpdateForm;
import com.duzz.backend.form.SignInForm;
import com.duzz.backend.form.SignUpForm;
import com.duzz.backend.service.MemberService;
import com.duzz.backend.component.SecurityProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@Tag(name = "Member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final SecurityProvider securityProvider;

    @PostMapping("/signup")
    @Operation(summary = "회원가입을 시도합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공 시", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "회원가입 성공"))),
            @ApiResponse(responseCode = "400", description = "이미 존재하는 학번의 경우", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "이미 존재하는 학번입니다.")))
    })
    public ResponseEntity<?> signUp(@RequestBody SignUpForm form) {
        try {
            memberService.signUp(form);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    @Operation(summary = "로그인을 시도하여 JWT 토큰을 반환합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "로그인 성공 시", content = @Content(schema = @Schema(implementation = JwtTokenDto.class), examples = @ExampleObject(value = "{\"grantType\":\"Bearer\",\"accessToken\":\"" + "(JWT 토큰)" + "\"}"))),
            @ApiResponse(responseCode = "401", description = "로그인 실패 시", content = @Content(schema = @Schema(implementation = JwtTokenDto.class), examples = @ExampleObject(value = "{\"grantType\":\"Bearer\",\"accessToken\":\"\"}")))
    })
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
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(empty);
        }
    }

    @PostMapping("/update")
    @Operation(summary = "(JWT 토큰 필요) 회원 정보를 수정합니다. 모든 필드는 선택사항으로, 수정하려는 필드만 입력하면 됩니다.")
    public ResponseEntity<?> updateMember(@RequestBody MemberUpdateForm form) {
        try {
            var id = securityProvider.getCurrentUserId();
            memberService.updateMember(id, form);
            return ResponseEntity.ok("회원 정보 수정 성공");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    @Operation(summary = "(JWT 토큰 필요) 전송된 JWT 토큰을 기반으로 내 정보를 반환합니다. Swagger에서는 불가능하고 Postman 등을 사용해야 함.")
    public ResponseEntity<?> getMyInfo() {
        try {
            var id = securityProvider.getCurrentUserId();
            return ResponseEntity.ok(memberService.getMember(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list/major")
    @Operation(summary = "학과를 기반으로 회원 목록을 반환합니다.")
    public ResponseEntity<?> getMemberListByMajor(@RequestParam String major, @RequestParam(defaultValue = "0") Integer page) {
        try {
            return ResponseEntity.ok(memberService.getMembersByMajor(major, page));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
