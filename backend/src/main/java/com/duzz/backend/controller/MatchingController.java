package com.duzz.backend.controller;

import com.duzz.backend.service.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/match")
@Tag(name = "Match")
@RestController
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/match")
    @Operation(summary = "멘토-멘티 매칭을 수행합니다.")
    public ResponseEntity<String> matchMentorsAndMentees() {
        matchingService.mentorMenteeMatch();
        return ResponseEntity.ok("매칭 성공");
    }
}
