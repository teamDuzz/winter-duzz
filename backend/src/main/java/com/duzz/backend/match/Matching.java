package com.duzz.backend.match;

import com.duzz.backend.entity.Member;
import com.duzz.backend.entity.MemberSubject;
import com.duzz.backend.service.MemberService;

import java.util.ArrayList;
import java.util.List;

public class Matching {
    static MemberService memberService;
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
            for(Mentee mentee : match.mentees()){
                //멘티에게 멘토 매칭 결과 전송
                //멘토에게 멘티 매칭 결과 전송
            }
        }
    }
}
