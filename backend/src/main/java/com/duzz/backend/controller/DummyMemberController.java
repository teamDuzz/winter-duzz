package com.duzz.backend.controller;


import com.duzz.backend.component.SecurityProvider;
import com.duzz.backend.service.DummyMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dummy")
@Tag(name = "더미 멤버 API")
@RequiredArgsConstructor
public class DummyMemberController {
    private final DummyMemberService dummyMemberService;
    private final SecurityProvider securityProvider;

    @PostMapping("/add")
    @Operation(summary = "(관리용) 더미 멤버 추가")
    public ResponseEntity<?> addDummies(@RequestParam int mentorCount, @RequestParam int menteeCount) {
        securityProvider.checkAdmin();
        return ResponseEntity.ok(dummyMemberService.addDummies(mentorCount, menteeCount));
    }

    @PostMapping("/clear")
    @Operation(summary = "(관리용) 더미 멤버 모두 삭제")
    public ResponseEntity<?> clearDummies() {
        securityProvider.checkAdmin();
        return ResponseEntity.ok(dummyMemberService.clearDummies());
    }
}
