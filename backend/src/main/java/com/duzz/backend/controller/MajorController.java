package com.duzz.backend.controller;

import com.duzz.backend.service.MajorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/major")
@Tag(name = "Major")
@RequiredArgsConstructor
public class MajorController {
    private final MajorService majorService;

    @PostMapping("/add/{majorName}")
    @Operation(summary = "학과를 추가합니다. (관리자용 기능)")
    public ResponseEntity<?> addMajor(@PathVariable String majorName) {
        try {
            majorService.addMajor(majorName);
            return ResponseEntity.ok("학과 추가 성공");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    @Operation(summary = "학과 목록을 반환합니다.")
    public ResponseEntity<?> getMajorList() {
        return ResponseEntity.ok(majorService.getMajorList());
    }

    @GetMapping("/subject/{majorName}")
    @Operation(summary = "학과에 속한 과목 목록을 반환합니다.")
    public ResponseEntity<?> getSubjectByMajor(@PathVariable String majorName) {
        try {
            return ResponseEntity.ok(majorService.getSubjectByMajor(majorName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
