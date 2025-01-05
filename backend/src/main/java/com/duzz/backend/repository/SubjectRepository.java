package com.duzz.backend.repository;


import com.duzz.backend.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    Optional<Subject> findByName(String name);

    Page<Subject> findByNameContainingIgnoreCaseOrProfessorContainingIgnoreCase(String name, String professor, Pageable pageable);
}
