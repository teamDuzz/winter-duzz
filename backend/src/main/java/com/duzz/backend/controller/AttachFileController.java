package com.duzz.backend.controller;

import com.duzz.backend.service.AttachFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@Tag(name = "파일 업로드 및 다운로드")
@RequiredArgsConstructor
public class AttachFileController {
    private final AttachFileService attachFileService;

    @PostMapping("/upload")
    @Operation(summary = "파일 업로드 Swagger에서 작동하지 않음", requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = MultipartFile.class, type = "file"))))
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            var uuid = attachFileService.uploadFile(file);
            return ResponseEntity.ok(uuid);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/download/{fileName}")
    @Operation(summary = "파일 다운로드")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) {
        try {
            var headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(attachFileService.downloadFile(fileName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/delete/{fileName}")
    @Operation(summary = "파일 삭제")
    public ResponseEntity<?> deleteFile(@PathVariable("fileName") String fileName) {
        try {
            attachFileService.deleteFile(fileName);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
