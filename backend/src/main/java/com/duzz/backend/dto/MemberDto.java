package com.duzz.backend.dto;

import com.duzz.backend.entity.Member;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MemberDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String major;
    private String interest;
    private Boolean isMentor;
    private Boolean matchingOption;
    private List<String> subjects;
    private String mentorId;
    private List<String> menteeIds;

    public static MemberDto from(Member member) {
        return builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .major(member.getMajor() == null ? null : member.getMajor().getName())
                .interest(member.getInterest())
                .isMentor(member.getIsMentor())
                .matchingOption(member.getMatchingOption())
                .subjects(member.getSubjects().stream()
                        .map(memberSubject -> memberSubject.getSubject().getName())
                        .toList())
                .mentorId(member.getMentor() == null ? null : member.getMentor().getId())
                .menteeIds(member.getMentees().stream()
                        .map(Member::getId)
                        .toList())
                .build();
    }
}
