package com.duzz.backend.service;

import com.duzz.backend.entity.Member;
import com.duzz.backend.entity.MemberSubject;
import com.duzz.backend.form.MemberUpdateForm;
import com.duzz.backend.match.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final MemberService memberService;

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
                Mentor mentor = new Mentor(member.getName(), member.getId(), member.getMajor().toString(), subjects);
                mentor.setSchedule(timetable);
                mentors.add(mentor);
            } else {
                // Mentee 생성하고 리스트에 추가
                //Mentee mentee = new Mentee(member.getName(), member.getId(),interest, subjects,options);
                //mentee.setSchedule(timetable);
                //mentees.add(mentee);
            }
        }

        // 멘토와 멘티 매칭
        List<Match> matches = MentorMenteeMatcher.matchProfiles(mentors, mentees);

        // 매칭 결과를 멤버에 반영
        for (Match match : matches) {
            // 멘티에게 멘토를 추가
            for (Mentee mentee : match.mentees()) {
                MemberUpdateForm menteeForm = MemberUpdateForm.builder()
                        .name("a")  // 멘토 추가 후 멘티 업데이트
                        .build();
                memberService.updateMember(mentee.getNumber(), menteeForm);
            }

            // 멘토에게 멘티 리스트 추가
            MemberUpdateForm mentorForm = MemberUpdateForm.builder()
                    .name("a")  // 멘티 리스트 추가
                    .build();
            memberService.updateMember(match.mentor().getNumber(), mentorForm);
        }
    }
}
