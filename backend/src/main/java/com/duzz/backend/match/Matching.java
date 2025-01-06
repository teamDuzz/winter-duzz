package com.duzz.backend.match;

import com.duzz.backend.entity.Member;
import com.duzz.backend.entity.MemberSubject;
import com.duzz.backend.form.MemberUpdateForm;
import com.duzz.backend.service.MemberService;

import java.util.ArrayList;
import java.util.List;

public class Matching {
    private final MemberService memberService;

    // 생성자에서 MemberService 주입
    public Matching(MemberService memberService) {
        this.memberService = memberService;
    }
    public void MentorMenteeMatch(){
        List<Member> members = memberService.getAllMembers();
        List<Mentor> mentors =new ArrayList<>();
        List<Mentee> mentees = new ArrayList<>();
        for(Member member : members){
            int[][] timetable = MakeTimetable.makeTimetable(member);
            List<String>subjects= new ArrayList<>();
            for(MemberSubject subject : member.getSubjects()){
                subjects.add(subject.getSubject().getName());
            }
            if(member.getIsMentor()){
                Mentor mentor= new Mentor(member.getName(),member.getId(),member.getMajor().toString(), subjects);
                mentor.schedule = timetable;
                mentors.add(mentor);
            }else{
//                Mentee mentee = new Mentee(member.getName(),member.getId(),interst,subjects,option);
//                mentee.schedule = timetable;
//                mentees.add(mentee);
            }
        }
        List<Match> matches = MentorMenteeMatcher.matchProfiles(mentors, mentees);
        for (Match match : matches) {
            List<String> menteeNames = new ArrayList<>();
            for(Mentee mentee : match.mentees()){
                MemberUpdateForm menteeForm = MemberUpdateForm.builder()
                        .name("a")// 멘토추가로 변경
                        .build();
                memberService.updateMember(mentee.getNumber(),menteeForm);
                menteeNames.add(mentee.getName());
            }
            MemberUpdateForm mentorForm = MemberUpdateForm.builder()
                    .name("a")//멘티 리스트 추가
                    .build();
            memberService.updateMember(match.mentor().getNumber(),mentorForm);
        }
    }
}
