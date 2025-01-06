package com.duzz.backend.service;

import com.duzz.backend.entity.Member;
import com.duzz.backend.entity.MemberSubject;
import com.duzz.backend.match.*;
import com.duzz.backend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Transactional
    // 멘토-멘티 매칭 메소드
    public void mentorMenteeMatch() {
        // 멤버 목록 가져오기
        List<Member> members = memberService.getAllMembers();
        List<Mentor> mentors = new ArrayList<>();
        List<Mentee> mentees = new ArrayList<>();

        // 멘토와 멘티 구분하여 리스트에 추가
        for (Member member : members) {
            int[][] timetable = MakeTimetable.makeTimetable(member);
            List<String> subjects = new ArrayList<>();
            for (MemberSubject subject : member.getSubjects()) {
                subjects.add(subject.getSubject().getName());
            }
            if (member.getIsMentor()) {
                var major = member.getMajor() == null ? "" : member.getMajor().getName();
                Mentor mentor = new Mentor(member.getName(), member.getId(), major, subjects);
                mentor.setSchedule(timetable);
                mentors.add(mentor);
            } else {
                // Mentee 생성하고 리스트에 추가
                List<String> interests = member.getInterest() == null ? Collections.emptyList() : List.of(member.getInterest().split("\\|"));
                Mentee mentee = new Mentee(member.getName(), member.getId(), interests, subjects, false);
                mentee.setSchedule(timetable);
                mentees.add(mentee);
            }
        }

        // 멘토와 멘티 매칭
        List<Match> matches = MentorMenteeMatcher.matchProfiles(mentors, mentees);
//        var minseok = mentors.stream().filter(x -> x.getNumber().equals("202302582")).findAny().orElse(null);
//        if (minseok == null) throw new RuntimeException("유민석을 찾을 수 없습니다.");
//        List<Match> matches = List.of(
//                new Match(minseok, mentees)
//        );

        // 매칭 결과를 멤버에 반영
        for (Match match : matches) {
            // 멘티에게 멘토를 추가
            var memberMentor = memberRepository.findById(match.mentor().getNumber()).orElse(null);
            if (memberMentor == null) {
                throw new RuntimeException("멘토를 찾을 수 없습니다: " + match.mentor().getNumber());
            }
            for (Mentee mentee : match.mentees()) {
                var memberMentee = memberRepository.findById(mentee.getNumber()).orElse(null);
                if (memberMentee == null) {
                    throw new RuntimeException("멘티를 찾을 수 없습니다: " + mentee.getNumber());
                }
                matchInternal(memberMentor, memberMentee);
            }
        }
    }

    public void match(String mentorId, String menteeId) {
        var mentor = memberRepository.findById(mentorId).orElse(null);
        var mentee = memberRepository.findById(menteeId).orElse(null);

        if (mentor == null) {
            throw new RuntimeException("멘토를 찾을 수 없습니다: " + mentorId);
        }
        if (mentee == null) {
            throw new RuntimeException("멘티를 찾을 수 없습니다: " + menteeId);
        }

        matchInternal(mentor, mentee);
    }

    public void unmatch(String mentorId, String menteeId) {
        var mentor = memberRepository.findById(mentorId).orElse(null);
        var mentee = memberRepository.findById(menteeId).orElse(null);

        if (mentor == null) {
            throw new RuntimeException("멘토를 찾을 수 없습니다: " + mentorId);
        }
        if (mentee == null) {
            throw new RuntimeException("멘티를 찾을 수 없습니다: " + menteeId);
        }

        unmatchInternal(mentor, mentee);
    }

    public void clear() {
        List<Member> members = memberService.getAllMembers();
        for (Member member : members) {
            member.setMentor(null);
            member.getMentees().clear();
        }
        memberRepository.saveAll(members);
    }


    private void matchInternal(Member mentor, Member mentee) {
        System.out.println("Matched: " + mentor.getName() + " - " + mentee.getName());
        mentor.getMentees().add(mentee);
        mentee.setMentor(mentor);

        memberRepository.save(mentor);
    }

    private void unmatchInternal(Member mentor, Member mentee) {
        System.out.println("Unmatched: " + mentor.getName() + " - " + mentee.getName());
        mentor.getMentees().remove(mentee);
        mentee.setMentor(null);

        memberRepository.save(mentor);
    }
}
