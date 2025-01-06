package com.duzz.backend.service;

import com.duzz.backend.entity.MemberSubject;
import com.duzz.backend.repository.MajorRepository;
import com.duzz.backend.repository.MemberRepository;
import com.duzz.backend.repository.MemberSubjectRepository;
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
    private final MemberSubjectRepository memberSubjectRepository;

    @Transactional
    public void addSubjectFor(String memberId, String subjectId) {
        var member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생입니다."));
        var subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과목입니다."));

        if (member.getSubjects().stream().anyMatch(ms -> ms.getSubject().getId().equals(subjectId))) {
            throw new IllegalArgumentException("이미 수강 중인 과목입니다.");
        }

        member.getSubjects().add(MemberSubject.of(member, subject));

        memberRepository.save(member);
    }

    @Transactional
    public void deleteSubjectFor(String memberId, String subjectId) {
        var member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생입니다."));
        var subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과목입니다."));

        var memberSubject = memberSubjectRepository.findByMemberAndSubject(member, subject).orElseThrow(() -> new IllegalArgumentException("수강 중이지 않은 과목입니다."));


        member.getSubjects().removeIf(ms -> ms.getSubject().getId().equals(subjectId));
        subject.getMembers().removeIf(ms -> ms.getMember().getId().equals(memberId));

        memberRepository.save(member);
        subjectRepository.save(subject);
        memberSubjectRepository.delete(memberSubject);
    }
}
