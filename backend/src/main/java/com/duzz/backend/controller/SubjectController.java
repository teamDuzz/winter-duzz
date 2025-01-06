package com.duzz.backend.controller;

import com.duzz.backend.component.SecurityProvider;
import com.duzz.backend.form.SubjectAddForm;
import com.duzz.backend.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subject")
@Tag(name = "과목 관리")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    private final SecurityProvider securityProvider;

    @PostMapping("/add")
    @Operation(summary = "과목을 추가합니다.")
    public ResponseEntity<?> addSubject(@RequestBody SubjectAddForm form) {
        try {
            securityProvider.checkAdmin();
            var subjectDto = SubjectAddForm.toDto(form);
            subjectService.addOrUpdateSubject(subjectDto);
            return ResponseEntity.ok("과목 추가 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
//            throw e;
        }
    }

    @PostMapping("/delete/{subjectId}")
    @Operation(summary = "과목을 삭제합니다.")
    public ResponseEntity<?> deleteSubject(@PathVariable String subjectId) {
        try {
            securityProvider.checkAdmin();
            subjectService.deleteSubject(subjectId);
            return ResponseEntity.ok("과목 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    @Operation(summary = "과목 목록을 조회합니다.")
    public ResponseEntity<?> getSubjects(@RequestParam(defaultValue = "") String keyword,
                                        @RequestParam(defaultValue = "0") int page) {
        try {
            return ResponseEntity.ok(subjectService.getSubjects(keyword, page));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
