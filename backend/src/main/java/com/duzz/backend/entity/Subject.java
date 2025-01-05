package com.duzz.backend.entity;

import com.duzz.backend.other.SubjectTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends BaseTimeEntity{
    @Id
    @Column(length = 20, nullable = false) // 과목번호
    private String id;
    @Column(length = 50, nullable = false) // 과목명
    private String name;
    @Column(length = 50, nullable = true) // 교수명
    private String professor;

    @ColumnDefault("3")
    private Integer credit; // 학점

    private SubjectTime time; // 수업 시간

    @ManyToOne
    private Major major; // 제공 학과

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Set<MemberSubject> members = new HashSet<>();
}
