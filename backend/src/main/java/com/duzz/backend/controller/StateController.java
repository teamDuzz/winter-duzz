package com.duzz.backend.controller;

import com.duzz.backend.service.StateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Tag(name = "State")
@RequiredArgsConstructor
public class StateController {
    private final StateService stateService;

    @GetMapping("/state")
    @Operation(summary = "상태를 반환합니다.")
    public ResponseEntity<?> getState() {
        return ResponseEntity.ok(stateService.getState());
    }

    @PostMapping("/state")
    @Operation(summary = "상태를 변경합니다.")
    public ResponseEntity<?> setState(Integer state) {
        stateService.setState(state);
        return ResponseEntity.ok("상태 변경 성공");
    }
}
