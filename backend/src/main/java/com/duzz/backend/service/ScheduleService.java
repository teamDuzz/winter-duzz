package com.duzz.backend.service;

import com.duzz.backend.entity.MemberSubject;
import com.duzz.backend.entity.Subject;
import com.duzz.backend.repository.MajorRepository;
import com.duzz.backend.repository.MemberRepository;
import com.duzz.backend.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final SubjectRepository subjectRepository;
    private final MajorRepository majorRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addSubjectFor(String memberId, String subjectId) {
        var member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 학생입니다.");
        }
        var subject = subjectRepository.findById(subjectId).orElse(null);
        if (subject == null) {
            throw new IllegalArgumentException("존재하지 않는 과목입니다.");
        }

        if (member.getSubjects().stream().anyMatch(ms -> ms.getSubject().getId().equals(subjectId))) {
            throw new IllegalArgumentException("이미 수강 중인 과목입니다.");
        }

        member.getSubjects().add(MemberSubject.of(member, subject));

        memberRepository.save(member);
    }
}
