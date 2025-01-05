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
    private Boolean isMentor;
    private List<String> subjects;

    public static MemberDto from(Member member) {
        var majorName = member.getMajor() == null ? null : member.getMajor().getName();
        return builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .major(majorName)
                .isMentor(member.getIsMentor())
                .subjects(member.getSubjects().stream()
                        .map(memberSubject -> memberSubject.getSubject().getName())
                        .toList())
                .build();
    }
}
