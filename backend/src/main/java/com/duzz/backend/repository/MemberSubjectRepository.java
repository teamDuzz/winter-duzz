package com.duzz.backend.repository;

import com.duzz.backend.entity.Member;
import com.duzz.backend.entity.MemberSubject;
import com.duzz.backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberSubjectRepository extends JpaRepository<MemberSubject, String> {
    Optional<MemberSubject> findByMemberAndSubject(Member member, Subject subject);
}
