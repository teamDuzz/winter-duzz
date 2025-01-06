package com.duzz.backend.repository;


import com.duzz.backend.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    Optional<Subject> findByName(String name);

    @Query(
"""
SELECT s FROM Subject s
WHERE s.name LIKE %:keyword% OR s.professor LIKE %:keyword% OR s.id LIKE %:keyword%
""")
    Page<Subject> findByKeyword(String keyword, Pageable pageable);
}
