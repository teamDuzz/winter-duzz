package com.duzz.backend.repository;

import com.duzz.backend.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);

    Page<Member> findAllByMajor_Name(String majorName, Pageable pageable);
}
