package com.duzz.backend.repository;

import com.duzz.backend.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MajorRepository extends JpaRepository<Major, String> {
    Optional<Major> findByName(String name);
}
