package com.duzz.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSubject extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public static MemberSubject of(Member member, Subject subject) {
        return MemberSubject.builder()
                .member(member)
                .subject(subject)
                .build();
    }
}
