package com.duzz.backend.repository;

import com.duzz.backend.entity.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachFileRepository extends JpaRepository<AttachFile, String> {
    Optional<AttachFile> findByUuid(String uuid);
    Optional<AttachFile> findByFileName(String fileName);
}
