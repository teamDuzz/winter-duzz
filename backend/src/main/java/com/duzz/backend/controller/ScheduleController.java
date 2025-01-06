package com.duzz.backend.controller;

import com.duzz.backend.service.ScheduleService;
import com.duzz.backend.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
@Tag(name = "시간표 관리")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/add/{subjectId}")
    @Operation(summary = "(JWT 토큰 필요) 학생의 시간표에 과목을 추가합니다.")
    public ResponseEntity<?> addSubject(@PathVariable String subjectId) {
        try {
            var memberId = SecurityUtil.getCurrentUserId();
            scheduleService.addSubjectFor(memberId, subjectId);
            return ResponseEntity.ok("과목 추가 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/delete/{subjectId}")
    @Operation(summary = "(JWT 토큰 필요) 학생의 시간표에서 과목을 제거합니다.")
    public ResponseEntity<?> deleteSubject(@PathVariable String subjectId) {
        try {
            var memberId = SecurityUtil.getCurrentUserId();
            scheduleService.deleteSubjectFor(memberId, subjectId);
            return ResponseEntity.ok("과목 제거 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
