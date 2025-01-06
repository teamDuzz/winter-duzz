package com.duzz.backend.service;

import com.duzz.backend.entity.AttachFile;
import com.duzz.backend.repository.AttachFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachFileService {
    private final AttachFileRepository attachFileRepository;
    private final String uploadPath = "assets/upload/";

    public String uploadFile(MultipartFile multipartFile) {
        var originalFileName = multipartFile.getOriginalFilename();
        var uuid = UUID.randomUUID().toString();
        var fileName = uuid + "_" + originalFileName;
        var file = new File(uploadPath + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            byte[] bytes = multipartFile.getBytes();
//            multipartFile.transferTo(file);
            Files.write(file.toPath(), bytes);
            var attachFile = AttachFile.builder()
                    .uuid(uuid)
                    .fileName(fileName)
                    .originalFileName(originalFileName)
                    .build();
            attachFileRepository.save(attachFile);

            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteFile(String fileName) {
        var attachFile = attachFileRepository.findByFileName(fileName).orElseThrow();
        var file = new File(uploadPath + attachFile.getFileName());

        if (file.exists()) {
            file.delete();
            attachFileRepository.delete(attachFile);
        }
    }

    public byte[] downloadFile(String fileName) throws IOException {
        var file = getFile(fileName);
        return Files.readAllBytes(file.toPath());
    }

    private File getFile(String fileName) {
        var attachFile = attachFileRepository.findByFileName(fileName).orElseThrow();
        return new File(uploadPath + attachFile.getFileName());
    }
}
