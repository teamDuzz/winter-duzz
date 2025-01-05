package com.duzz.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Major extends BaseTimeEntity {
    @Id
    @Column(length = 20, nullable = false) // 학과명
    private String name;

    @OneToMany(mappedBy = "major", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Member> members;

    @OneToMany(mappedBy = "major", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Subject> subjects;
}
