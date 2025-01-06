package com.duzz.backend.controller;

import com.duzz.backend.component.SecurityProvider;
import com.duzz.backend.service.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/match")
@Tag(name = "Match")
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingService matchingService;
    private final SecurityProvider securityProvider;

    @PostMapping("/match")
    @Operation(summary = "(관리자용) 멘토-멘티 매칭을 수행합니다.")
    public ResponseEntity<?> matchMentorsAndMentees() {
        try {
            securityProvider.checkAdmin();
            matchingService.mentorMenteeMatch();
            return ResponseEntity.ok("매칭 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/match/{mentorId}/{menteeId}")
    @Operation(summary = "(관리자용, 테스트용) 멘토-멘티 매칭을 수행합니다.")
    public ResponseEntity<?> matchMentorAndMentee(String mentorId, String menteeId) {
        try {
            securityProvider.checkAdmin();
            matchingService.match(mentorId, menteeId);
            return ResponseEntity.ok("매칭 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/unmatch/{mentorId}/{menteeId}")
    @Operation(summary = "(관리자용, 테스트용) 멘토-멘티 매칭을 해제합니다.")
    public ResponseEntity<?> unmatchMentorAndMentee(String mentorId, String menteeId) {
        try {
            securityProvider.checkAdmin();
            matchingService.unmatch(mentorId, menteeId);
            return ResponseEntity.ok("매칭 해제 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/clear")
    @Operation(summary = "(관리자용) 모든 매칭을 해제합니다.")
    public ResponseEntity<?> clearMatches() {
        try {
            securityProvider.checkAdmin();
            matchingService.clear();
            return ResponseEntity.ok("모든 매칭 해제 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
